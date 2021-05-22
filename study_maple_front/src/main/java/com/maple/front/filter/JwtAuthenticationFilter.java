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
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maple.front.config.PrincipalDetails;
import com.maple.front.entity.Member;
import com.maple.front.repository.MemberRepository;
import com.maple.front.util.ConstantUtil;
import com.maple.front.util.StringUtil;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
	
	private final AuthenticationManager authenticationManager;
	private final MemberRepository memberRepisotory;
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		
		try {
			ObjectMapper om = new ObjectMapper();
			Member mbr = om.readValue(request.getInputStream(), Member.class);
				
			UsernamePasswordAuthenticationToken authenticationToken =
					new UsernamePasswordAuthenticationToken(mbr.getEmail(), mbr.getPassword());
				
			//PrincipalDetailService의 loadUserByName함수 실행
			Authentication authentication =
					authenticationManager.authenticate(authenticationToken);
				
			PrincipalDetails principalDetails = (PrincipalDetails)authentication.getPrincipal();
			return authentication;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		
		PrincipalDetails principalDetails = (PrincipalDetails)authResult.getPrincipal();
		
		// Hash암호 방식
		// token create
		String jwtToken = JWT.create()
				.withSubject(principalDetails.getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis()+(60000 * 10))) // 10분
				.withClaim("id", principalDetails.getMember().getEmail())
				.withClaim("username", principalDetails.getMember().getMbrnm())
				.sign(Algorithm.HMAC512("cos"));
		
		Cookie accessToken = new Cookie(ConstantUtil.ACCESS_TOKEN_NAME, jwtToken);
		accessToken.setHttpOnly(true);
		accessToken.setMaxAge((int) (System.currentTimeMillis()+(60000 * 10)));
		response.addCookie(accessToken);
		
		// refresh token이 null인 경우 재발급
		Cookie[] cookieList = request.getCookies();
		String refreshToken = null;
		// redis -> check
		if (cookieList != null && cookieList.length > 0) {
			for (Cookie cookie : cookieList) {
				if (cookie.getName().equals(ConstantUtil.REFRESH_TOKEN_NAME)) {
					refreshToken = cookie.getValue();
				}
			}
		}
		
		// cookie에 없는 경우 redis조회
		if (StringUtil.isEmpty(refreshToken)) {
			//refreshToken = RedisUtil.getData(principalDetails.getMember().getEmail());
			if (StringUtil.isEmpty(refreshToken)) {
				String refreshJwtToken = JWT.create()
						.withSubject(principalDetails.getUsername())
						.withExpiresAt(new Date(System.currentTimeMillis()+(60000 * 60 * 24) * 2)) // 2주
						.withClaim("id", principalDetails.getMember().getEmail())
						.withClaim("username", principalDetails.getMember().getMbrnm())
						.sign(Algorithm.HMAC512("cos"));
				
				//redis 저장
				// email -> key token -> value
				//RedisUtil.setDataExpire(principalDetails.getMember().getEmail(),refreshJwtToken, ConstantUtil.REFRESH_TOKEN_VALIDATION_SECOND); //2주간 보관
				
				// cookie 저장
				Cookie refreshJwtTokenCookie = new Cookie(ConstantUtil.REFRESH_TOKEN_NAME, refreshJwtToken);
				refreshJwtTokenCookie.setHttpOnly(true);
				refreshJwtTokenCookie.setMaxAge((int)(System.currentTimeMillis()+(60000 * 60 * 24) * 2));
				response.addCookie(refreshJwtTokenCookie);
			}
		}
		
	}
	
}
