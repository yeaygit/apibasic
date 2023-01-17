package com.example.apibasic.jpabasic;

import com.example.apibasic.post.dto.PageRequestDTO;
import com.example.apibasic.post.dto.PageResponseDTO;
import com.example.apibasic.post.entity.PostEntity;
import com.example.apibasic.post.repository.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;

import java.util.List;

@SpringBootTest
class PageTest {

    @Autowired
    PostRepository postRepository;
    @BeforeEach
    void bulkInsert(){
        for (int i=1;i<=433;i++){
            PostEntity post = PostEntity.builder()
                    .title("안녕!~" + i)
                    .writer("김말종" + i)
                    .content("아무말 아무말 아무말 ~~" + i)
                    .build();
            postRepository.save(post);
        }
    }

    @Test
    void pagingTest(){

        //클라이언트가 전달한 페이지 정보
        PageRequestDTO dto=PageRequestDTO.builder()
                .page(4)
                .sizePerPage(10)
                .build();

        //페이지 정보 생성
        PageRequest pageInfo = PageRequest.of(dto.getPage()-1,
                dto.getSizePerPage(),
                Sort.Direction.DESC,
                "createDate");//내림차정렬

        Page<PostEntity> postEntities = postRepository.findAll(pageInfo);

        //실제 조회된 데이터
        int totalPages=postEntities.getTotalPages();
        List<PostEntity> contents = postEntities.getContent();
        postEntities.getTotalPages();
        long totalElements = postEntities.getTotalElements();
        boolean prev = postEntities.getPageable().hasPrevious();


        System.out.println("contents.size() = " + contents.size());
        System.out.println("totalElements = " + totalElements);
        System.out.println("totalPages = " + totalPages);
        contents.forEach(System.out::println);
    }

    @Test
    @DisplayName("제목에 3이 포함된 결과를 검색 후 1페이지 정보를 조회해야 한다.")
    void pageTest2() {
        //given
        String title = "3";
        PageRequest pageRequest = PageRequest.of(
                46,
                10,
                Sort.Direction.DESC,
                "createDate");

        Slice<PostEntity> postEntityPage
                = postRepository.findByTitleContaining(title, pageRequest);

        List<PostEntity> contents = postEntityPage.getContent();

        boolean next = postEntityPage.hasNext();
        boolean prev = postEntityPage.hasPrevious();
        System.out.println("prev = " + prev);
        System.out.println("next = " + next);


        contents.forEach(System.out::println);

        // 페이지 정보
        PageResponseDTO<PostEntity> dto
                = new PageResponseDTO<PostEntity>((Page<PostEntity>) postEntityPage);

        System.out.println("dto = " + dto);
    }
}
