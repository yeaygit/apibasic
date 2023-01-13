package com.example.apibasic.post.api;

import com.example.apibasic.post.dto.*;
import com.example.apibasic.post.entity.PostEntity;
import com.example.apibasic.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

//리소스 : 게시물(Post)

/*
 *  게시물 목록 조회: /posts -GET
 *  게시물 개별 조회 : /posts/{id} -GET
 *  게시물 등록 조회: /posts -POST
 *  게시물 수정 : /posts/{id} -PATCH
 *  게시물 삭제 : /posts/{id} -DELETE
 */
@RestController
@Slf4j
@RequiredArgsConstructor //생성자 자동생성(자동 주입)
@RequestMapping("/posts")
public class PostApiController {

    //postrepository에게 의존하는 관계
    private final PostRepository postRepository;

    //@Autowired //스프링 컨테이너에게 의존 객체를 자동 주입해달라
//    public PostApiController(PostRepository postRepository){
//        this.postRepository=postRepository; //불변성 유지가 필요하기 때문에 생성자
//    }


//    public void setPostRepository(PostRepository postRepository) {
//        this.postRepository = postRepository;
//    }
    //setter주입은 쓰면 안됨 -> 내가 원할 때 반복호출 가능


    //게시물 목록 조회
    @GetMapping
    public ResponseEntity<?> list(){
        log.info("/posts GET request");
        List<PostEntity> list = postRepository.findAll();

        // 엔터티 리스트를 DTO리스트로 변환해서 클라이언트에 응답
        List<PostResponseDTO> responseDTOList=list.stream()
                .map(PostResponseDTO::new)
                .collect(Collectors.toList());

        PostListResponseDTO listResponseDTO=
                PostListResponseDTO.builder()
                        .count(responseDTOList.size())
                        .posts(responseDTOList)
                        .build();
        return ResponseEntity
                .ok()
                .body(listResponseDTO)
                ;
    }

    //게시물 개별조회
    @GetMapping("/{postNo}")
    public ResponseEntity<?> detail(@PathVariable("postNo") Long postNo){ //@PathVariable Long postNo 가능
        log.info("/posts/{} GET request",postNo);
        PostEntity post = postRepository.findOne(postNo);

//        PostResponseDTO responseDTO= PostResponseDTO.builder()
//                .author(post.getWriter())
//                .title(post.getTitle())
//                .content(post.getContent())
//                .hashTags(post.getHashTags())
//                .regDate(post.getCreateDate())
//                .modifyDate(post.getModifyDate()).build();

        PostDetailResponseDTO dto= new PostDetailResponseDTO(post);

        return ResponseEntity
                .ok()
                .body(dto);
    }

    //게시물 등록
    @PostMapping
    public ResponseEntity<?> create(@RequestBody PostCreateDTO createDTO){
        log.info("/posts POST request");
        log.info("게시물 정보 :{}",createDTO);

        //dto를 entity로 변환하는 과정 필요
        PostEntity entity = createDTO.toEntity();

        boolean flag = postRepository.save(entity);
        return flag
                ? ResponseEntity.ok().body("INSERT-SUCCESS")
                : ResponseEntity.badRequest().body("INSERT_FAIL")
                ;
    }

    //게시물 수정
    @PatchMapping("/{postNo}")
    public ResponseEntity<?> modify(
            @PathVariable Long postNo
            , @RequestBody PostModifyDTO modifyDTO
    ) {
        log.info("/posts/{} PATCH request", postNo);
        log.info("수정할 정보 : {}", modifyDTO);

        // 수정 전 데이터 조회하기
        PostEntity entity = postRepository.findOne(postNo);
        // 수정 진행
        String modTitle = modifyDTO.getTitle();
        String modContent = modifyDTO.getContent();

        if (modTitle != null) entity.setTitle(modTitle);
        if (modContent != null) entity.setContent(modContent);
        entity.setModifyDate(LocalDateTime.now());

        boolean flag = postRepository.save(entity);
        return flag
                ? ResponseEntity.ok().body("MODIFY-SUCCESS")
                : ResponseEntity.badRequest().body("MODIFY-FAIL")
                ;
    }

    //게시물 삭제
    @DeleteMapping("/{postNo}")
    public ResponseEntity<?> remove(@PathVariable Long postNo){
        log.info("/posts/{} Delete request",postNo);
        PostEntity post = postRepository.findOne(postNo);

        boolean flag = postRepository.delete(postNo);
        return flag
                ? ResponseEntity.ok().body("DELETE-SUCCESS")
                : ResponseEntity.badRequest().body("DELETE-FAIL")
                ;
    }

}
