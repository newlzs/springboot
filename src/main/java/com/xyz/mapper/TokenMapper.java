package com.xyz.mapper;

import com.xyz.pojo.Token;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
* @author lzs
* @Date 2020/3/21
*/
@Repository
public interface TokenMapper {
    public Token getToken(@Param("userId") long userId, @Param("client") int client);
    public Token getTokenByStr(@Param("token") String token);
    public int createToken(Token token);
    public int updateToken(Token token);
    public int deleteToken(String token);
}
