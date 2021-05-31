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
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maple.front.config.PrincipalDetails;
import com.maple.front.entity.Member;
import com.maple.front.entity.MemberRefreshToken;
import com.maple.front.repository.MemberRefreshRepository;
import com.maple.front.util.ConstantUtil;
import com.maple.front.util.PrincipalDetailUtil;
import com.maple.front.util.StringUtil;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
	
	private final AuthenticationManager authenticationManager;
	private final MemberRefreshRepository memberRefreshRepository;
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		
		try {

			ObjectMapper om = new ObjectMapper();
			Member mbr = om.readValue(request.getInputStream(), Member.class);
				
			// user info check
			UsernamePasswordAuthenticationToken authenticationToken =
					new UsernamePasswordAuthenticationToken(mbr.getMbr_email(), mbr.getMbr_pass());
				
			//PrincipalDetailService의 loadUserByName함수 실행
			Authentication authentication =
					authenticationManager.authenticate(authenticationToken);
			// 회원정보get
			PrincipalDetails principalDetails = (PrincipalDetails)authentication.getPrincipal();
			
			// static save
			PrincipalDetailUtil.principalDetails = principalDetails;
			return authentication;

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		
		// 회원정보 취득
		PrincipalDetails principalDetails = (PrincipalDetails)authResult.getPrincipal();
		
		// Hash암호 방식
		// token create
		String jwtToken = JWT.create()
				.withSubject(principalDetails.getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis()+(60000 * 10))) // 10분
				.withClaim("id", principalDetails.getMember().getMbr_email())
				.withClaim("username", principalDetails.getMember().getMbr_name())
				.sign(Algorithm.HMAC512("cos"));
		
		Cookie accessToken = new Cookie(ConstantUtil.ACCESS_TOKEN_NAME, jwtToken);
		accessToken.setHttpOnly(true);
		accessToken.setMaxAge((int) (System.currentTimeMillis()+(60000 * 10)));
		response.addCookie(accessToken);
		String refreshToken = "";
		
		//redis search
		Optional<MemberRefreshToken> mbrToken = memberRefreshRepository.findByEmail(principalDetails.getMember().getMbr_email());

		if (mbrToken.isPresent()) {  // -> not null
			long expireTime = mbrToken.get().getExpireTime();
			long now = System.currentTimeMillis();

			if (expireTime > now) {
				refreshToken = mbrToken.get().getToken();
			}
		}
		
		if (StringUtil.isEmpty(refreshToken)) {
			String refreshJwtToken = JWT.create()
					.withSubject(principalDetails.getUsername())
					.withExpiresAt(new Date(System.currentTimeMillis()+(60000 * 60) * 24)) // 1day
					.withClaim("id", principalDetails.getMember().getMbr_email())
					.withClaim("username", principalDetails.getMember().getMbr_name())
					.sign(Algorithm.HMAC512("cos"));
			
			// redis 저장
			MemberRefreshToken mbrRefreshToken = MemberRefreshToken.builder()
					.email(principalDetails.getMember().getMbr_email())
					.token(refreshJwtToken)
					.expireTime(System.currentTimeMillis() + (60000 * 60) * 24)
					.build();
				
			memberRefreshRepository.save(mbrRefreshToken);
		}

		// member 객체 저장
		request.setAttribute("userInfo", principalDetails.getMember());
		
	}
	
}
