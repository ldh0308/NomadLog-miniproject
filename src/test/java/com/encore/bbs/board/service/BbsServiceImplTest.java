package com.encore.bbs.board.service;

import com.encore.bbs.board.dto.BbsDTO;
import com.encore.bbs.board.dto.CountryDto;
import com.encore.bbs.board.mapper.BbsMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BbsServiceImplTest {

    @Autowired
    private BbsMapper bbsmapper;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @DisplayName("국가이름 넣기 테스트")
    @Test
    void insertBbs(BbsDTO bbs, String content) throws Exception {

        bbsmapper.insertBbs(bbs);

        //		//게시글 저장 아이디 반환(getLatestBbsId 추가 service)
       // Long savedBbsId = bbsmapper.getLatestBbsId();

        // 해시태그 저장을 위한 객체 생성
        //HashTag hashTag = new HashTag();
        //hashTag.setBbsId(savedBbsId); // 해당 객체에 저장아이디 반환
        //hashTag.setContent(content); // 해당 객체에 작성한 내용 반환

        //해시태그 인서트
        //bbsmapper.insertHashtag(hashTag);
        Long countryId = 1L;


        Long savedcountryBbsId = bbsmapper.getLatestBbsId();

        assertEquals(savedcountryBbsId, 1);
        CountryDto countryDto = new CountryDto();
//		countryDto.setCountryId(savedcountryBbsId);
        bbsmapper.selectCountryBbs(savedcountryBbsId);
    }
}