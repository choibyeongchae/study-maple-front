package com.maple.front.filter;

import java.io.IOException;
import java.util.Date;

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
import com.maple.front.repository.MemberRepository;
import com.maple.front.util.ConstantUtil;
import com.maple.front.util.StringUtil;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

	private MemberRepository memberRepository;
	private Authentication auth;
	
	public JwtAuthorizationFilter(AuthenticationManager authenticationManager, MemberRepository memberRepository) {
		super(authenticationManager);
		this.memberRepository = memberRepository;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		String refreshToken = "";
		
		try {
			Cookie[] cookieList = request.getCookies();
			String token = "";
			String accessToken = "";
			if (cookieList != null && cookieList.length > 0) {
				for (Cookie cookie : cookieList) {
					if (cookie.getName().equals(ConstantUtil.ACCESS_TOKEN_NAME)) {
						accessToken = cookie.getValue();
					} else if (cookie.getName().equals(ConstantUtil.REFRESH_TOKEN_NAME)) {
						// redis -> get
						refreshToken = cookie.getValue();
					}
				}
			}
			//header 확인
			
			if (StringUtil.isEmpty(accessToken) && StringUtil.isEmpty(refreshToken)) {
				chain.doFilter(request, response);
				return;
			}
			
			// access token 없을시 refresh token으로
			if (accessToken != null ) {
				
				token = accessToken;
			} else {
				if (refreshToken != null) {
					token = refreshToken;
				}
			}
			
			String email = JWT.require(Algorithm.HMAC512("cos"))
					.build()
					.verify(token)
					.getClaim("id")
					.asString();
			
			if (email != null) {
				Member member = memberRepository.findByEmail(email);
				
				PrincipalDetails principalDetails = new PrincipalDetails(member);
				Authentication authentication = 
						new UsernamePasswordAuthenticationToken(principalDetails, null,principalDetails.getAuthorities());
	
				
				if (StringUtil.isEmpty(accessToken)) {
					String jwtToken = JWT.create()
							.withSubject(principalDetails.getUsername())
							.withExpiresAt(new Date(System.currentTimeMillis()+(60000 * 10))) // 10분
							.withClaim("id", principalDetails.getMember().getEmail())
							.withClaim("username", principalDetails.getMember().getMbrnm())
							.sign(Algorithm.HMAC512("cos"));
	
					// access token 재발급
					Cookie cookie = new Cookie(ConstantUtil.ACCESS_TOKEN_NAME,jwtToken);
					cookie.setHttpOnly(true);
					response.addCookie(cookie);
				
				}
				
				SecurityContextHolder.getContext().setAuthentication(authentication);
				
			}
			chain.doFilter(request, response);
	
		} catch(TokenExpiredException e) {
			
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
						.withClaim("id", principalDetails.getMember().getEmail())
						.withClaim("username", principalDetails.getMember().getMbrnm())
						.sign(Algorithm.HMAC512("cos"));
	
				Cookie cookie = new Cookie(ConstantUtil.ACCESS_TOKEN_NAME,jwtToken);
				cookie.setHttpOnly(true);
				response.addCookie(cookie);
				
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
			chain.doFilter(request, response);
 		}
	}
	
}
