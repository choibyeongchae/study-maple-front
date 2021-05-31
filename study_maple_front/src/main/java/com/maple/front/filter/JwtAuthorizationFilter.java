package com.maple.front.filter;

import java.io.IOException;
import java.util.Date;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.maple.front.config.PrincipalDetails;
import com.maple.front.entity.Member;
import com.maple.front.entity.MemberRefreshToken;
import com.maple.front.repository.MemberRefreshRepository;
import com.maple.front.repository.MemberRepository;
import com.maple.front.util.ConstantUtil;
import com.maple.front.util.PrincipalDetailUtil;
import com.maple.front.util.StringUtil;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

	private MemberRepository memberRepository;
	private MemberRefreshRepository memberRefreshRepository;
	
	public JwtAuthorizationFilter(AuthenticationManager authenticationManager, MemberRepository memberRepository, MemberRefreshRepository memberRefreshRepository) {
		super(authenticationManager);
		this.memberRepository = memberRepository;
	}
	
	// 요청이 될때마다 실행
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		String refreshToken = "";

		try {
			Cookie[] cookieList = request.getCookies();
			String accessToken = "";
			if (cookieList != null && cookieList.length > 0) {
				for (Cookie cookie : cookieList) {
					if (cookie.getName().equals(ConstantUtil.ACCESS_TOKEN_NAME)) {
						accessToken = cookie.getValue();
					}
				}
			}
			
			// 화면으로 리턴
			if (StringUtil.isEmpty(accessToken) && StringUtil.isEmpty(refreshToken)) {
				chain.doFilter(request, response);
				return;
			}
			
			String email = JWT.require(Algorithm.HMAC512("cos"))
					.build()
					.verify(accessToken)
					.getClaim("id")
					.asString();
			
			if (email != null) {
				Member member = memberRepository.findByEmail(email);
				
				PrincipalDetails principalDetails = new PrincipalDetails(member);
				Authentication authentication = 
						new UsernamePasswordAuthenticationToken(principalDetails, null,principalDetails.getAuthorities());
				
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}

			chain.doFilter(request, response);
		} catch(TokenExpiredException e) {
			// 액세스토큰이 유효기간지났을때 처리
			PrincipalDetails principalDetail = PrincipalDetailUtil.principalDetails;
			if (!StringUtil.isEmpty(principalDetail)) {
				Optional<MemberRefreshToken> mbrRefresh = memberRefreshRepository.findByEmail(principalDetail.getMember().getMbr_email());

				if (mbrRefresh.isPresent()) {
					long expireTime = mbrRefresh.get().getExpireTime();
					long now = System.currentTimeMillis();

					if (expireTime > now) {
						refreshToken = mbrRefresh.get().getToken();
					} else {
						String refreshJwtToken = JWT.create()
								.withSubject(principalDetail.getUsername())
								.withExpiresAt(new Date(System.currentTimeMillis()+(60000 * 60) * 24)) // 1day
								.withClaim("id", principalDetail.getMember().getMbr_email())
								.withClaim("username", principalDetail.getMember().getMbr_name())
								.sign(Algorithm.HMAC512("cos"));
						
						// redis 저장
						MemberRefreshToken mbrRefreshToken = MemberRefreshToken.builder()
								.email(principalDetail.getMember().getMbr_email())
								.token(refreshJwtToken)
								.expireTime(System.currentTimeMillis() + (60000 * 60) * 24)
								.build();
						
						memberRefreshRepository.save(mbrRefreshToken);
						
						refreshToken = refreshJwtToken;
					}

				} else {
					String refreshJwtToken = JWT.create()
							.withSubject(principalDetail.getUsername())
							.withExpiresAt(new Date(System.currentTimeMillis()+(60000 * 60) * 24)) // 1day
							.withClaim("id", principalDetail.getMember().getMbr_email())
							.withClaim("username", principalDetail.getMember().getMbr_name())
							.sign(Algorithm.HMAC512("cos"));
					
					// redis 저장
					MemberRefreshToken mbrRefreshToken = MemberRefreshToken.builder()
							.email(principalDetail.getMember().getMbr_email())
							.token(refreshJwtToken)
							.expireTime(System.currentTimeMillis() + (60000 * 60) * 24)
							.build();
					
					memberRefreshRepository.save(mbrRefreshToken);
					
					refreshToken = refreshJwtToken;

				}
				
				// access token 만료의 경우
				String email = JWT.require(Algorithm.HMAC512("cos"))
						.build()
						.verify(refreshToken)
						.getClaim("id")
						.asString();
				
				if (!StringUtil.isEmpty(email)) {
					Member member = memberRepository.findByEmail(email);
					
					PrincipalDetails principalDetails = new PrincipalDetails(member);
					Authentication authentication = 
							new UsernamePasswordAuthenticationToken(principalDetails, null,principalDetails.getAuthorities());
	
					// access token 재발급
					String jwtToken = JWT.create()
							.withSubject(principalDetails.getUsername())
							.withExpiresAt(new Date(System.currentTimeMillis()+(60000 * 10))) // 10분
							.withClaim("id", principalDetails.getMember().getMbr_email())
							.withClaim("username", principalDetails.getMember().getMbr_name())
							.sign(Algorithm.HMAC512("cos"));
		
					Cookie cookie = new Cookie(ConstantUtil.ACCESS_TOKEN_NAME,jwtToken);
					cookie.setHttpOnly(true);
					response.addCookie(cookie);
					
					SecurityContextHolder.getContext().setAuthentication(authentication);
				}
			}

			chain.doFilter(request, response);
 		}
	}
	
}
