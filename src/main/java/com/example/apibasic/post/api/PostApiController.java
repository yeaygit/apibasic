package com.example.apibasic.post.api;

import com.example.apibasic.post.dto.*;
import com.example.apibasic.post.entity.PostEntity;
import com.example.apibasic.post.repository.PostRepository;
import com.example.apibasic.post.service.PostService;
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
    private final PostService postService;

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

        try {
            PostListResponseDTO listResponseDTO = postService.getList();

            return ResponseEntity
                    .ok()
                    .body(listResponseDTO)
                    ;
        } catch (Exception e) {
            return ResponseEntity
                    .notFound()
                    .build()
                    ;
        }
    }

    //게시물 개별조회
    @GetMapping("/{postNo}")
    public ResponseEntity<?> detail(@PathVariable("postNo") Long postNo){ //@PathVariable Long postNo 가능
        log.info("/posts/{} GET request",postNo);


        try {
            PostDetailResponseDTO dto=postService.getDetail(postNo);

            return ResponseEntity
                    .ok()
                    .body(dto);
        } catch (Exception e) {
            return ResponseEntity
                    .notFound()
                    .build(); //body에 데이터가 없으면 .build
        }
    }

    //게시물 등록
    @PostMapping
    public ResponseEntity<?> create(@RequestBody PostCreateDTO createDTO){
        log.info("/posts POST request");
        log.info("게시물 정보 :{}",createDTO);

        return postService.insert(createDTO)
                ? ResponseEntity.ok().body("INSERT-SUCCESS")
                : ResponseEntity.badRequest().body("INSERT-FAIL")
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

        return postService.update(postNo, modifyDTO)
                ? ResponseEntity.ok().body("MODIFY-SUCCESS")
                : ResponseEntity.badRequest().body("MODIFY-FAIL")
                ;

    }

    //게시물 삭제
    @DeleteMapping("/{postNo}")
    public ResponseEntity<?> remove(@PathVariable Long postNo){
        log.info("/posts/{} DELETE request", postNo);

        return postService.delete(postNo)
                ? ResponseEntity.ok().body("DELETE-SUCCESS")
                : ResponseEntity.badRequest().body("DELETE-FAIL")
                ;

    }

}
