package com.datn.hoang.controller;

import com.datn.hoang.repository.ArticleRepository;
import com.datn.hoang.services.ArticleService;
import com.datn.hoang.services.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/article")
public class ArticleController {
    private final Logger log = LoggerFactory.getLogger(ArticleController.class);
    private final ArticleService articleService;

    private final ArticleRepository articleRepository;

    public ArticleController(ArticleService articleService, ArticleRepository articleRepository) {
        this.articleService = articleService;
        this.articleRepository = articleRepository;
    }

    //Lấy tất cả bài viết có phân trang
    @GetMapping("")
    public ResponseEntity<List<Map<String, Object>>> getAllArticleNewest() {
        List<Map<String, Object>> result = articleService.getAllArticleNewest();
        return ResponseEntity.ok().body(result);
    }

    //Lấy bài viết theo id
    @GetMapping("/view/{id}")
    public ResponseEntity<List<Map<String, Object>>> getArticleById(@PathVariable Long id) {
        log.debug("REST request to get Article : {}", id);
        List<Map<String, Object>> result = articleService.getArticleById(id);
        return ResponseEntity.ok().body(result);
    }

    //Lấy tất cả tin đăng theo trang thái bài viết ( chờ duyệt / duyệt )
    @GetMapping("/approved-status")
    public ResponseEntity<List<Map<String, Object>>> getAllArticleWithApprovedStatus(Pageable page,@RequestParam Long approvedStatus) {
        List<Map<String, Object>> result = articleService.getAllArticleWithApprovedStatus(page, approvedStatus);
        return ResponseEntity.ok().body(result);
    }

    //Lấy tất cả tin đăng trong màn chờ duyệt
    @GetMapping("/wait-status")
    public ResponseEntity<List<Map<String, Object>>> getAllArticleByNotApproved(Pageable page,@RequestParam Long articleStatus) {
        List<Map<String, Object>> result = articleService.getAllArticleByNotApproved(page, articleStatus);
        return ResponseEntity.ok().body(result);
    }

    //Lấy tất cả tin đăng trong màn đã duyệt
    @GetMapping("/ok-status")
    public ResponseEntity<List<Map<String, Object>>> getAllArticleByApproved(Pageable page,@RequestParam Long articleStatus, @RequestParam Long articleApproved) {
        List<Map<String, Object>> result = articleService.getAllArticleByApproved(page, articleStatus, articleApproved);
        return ResponseEntity.ok().body(result);
    }

    //Lấy tất cả tin đăng đã bị khóa
    @GetMapping("/article-blocked")
    public ResponseEntity<List<Map<String, Object>>> getAllArticleBlocked(Pageable page) {
        List<Map<String, Object>> result = articleService.getAllArticleBlocked(page);
        return ResponseEntity.ok().body(result);
    }

    @PutMapping("/{id}/approve")
    public ServiceResult<ArticleDTO> ApproveArticle(@PathVariable Long id, @RequestParam Long articleApproved, @RequestParam Long articleStatus, @RequestParam Long articleNotApproved) {
        return articleService.ApproveArticle(id, articleApproved, articleStatus, articleNotApproved);
    }

    @PutMapping("/{id}/not-approve")
    public ServiceResult<ArticleDTO> NotApproveArticle(@PathVariable Long id, @RequestParam Long articleApproved, @RequestParam Long articleStatus) {
        return articleService.NotApproveArticle(id, articleApproved, articleStatus);
    }

    //Block bài đăng
    @PatchMapping("/{id}/block")
    public ServiceResult<ArticleDTO> BlockArticle(@PathVariable Long id, @RequestParam Long articleBlocked) {
        return articleService.BlockArticle(id, articleBlocked);
    }

    //Search với phân trang
    @PostMapping(path = "/search")
    public ResponseEntity<ServiceResult> SearchArticleWithPagination(@RequestBody SearchDTO searchOption) {
        log.debug("REST request to search Article by Searchoption: {}");
        ServiceResult<Map<String, Object>> result = articleService.searchArticleWithPagination(searchOption);
        return ResponseEntity.ok().body(result);
    }

    //Search với phân trang đã được duyệt
    @PostMapping(path = "/search2")
    public ResponseEntity<ServiceResult> SearchArticleWithPagination2(@RequestBody SearchDTO searchOption) {
        log.debug("REST request to search Article by Searchoption: {}");
        ServiceResult<Map<String, Object>> result = articleService.searchArticleWithPagination2(searchOption);
        return ResponseEntity.ok().body(result);
    }

    //Search với phân trang mới nhất
    @PostMapping(path = "/search-newest")
    public ResponseEntity<ServiceResult> SearchArticleNewest(@RequestBody SearchDTO searchOption) {
        log.debug("REST request to search Article by Searchoption: {}");
        ServiceResult<Map<String, Object>> result = articleService.searchArticleWithNewsest(searchOption);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping(path = "/create")
    public ResponseEntity create(@RequestBody(required = false) RoomDTO roomDTO){
        ServiceResult<RoomDTO> result = articleService.create(roomDTO);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping(path = "/update")
    public ResponseEntity update(@RequestBody(required = false) ArticleUpdateDTO articleUpdateDTO){
        ServiceResult<RoomDTO> result = articleService.update(articleUpdateDTO);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping(path = "/count/cho-duyet")
    public ResponseEntity<ServiceResult> countNotStatus(){
        int total = articleRepository.countArticleByNotApproved();
        Map<String, Object> output = new HashMap<>();
        output.put("total", total);
        ServiceResult serviceResult = new ServiceResult<Map<String, Object>>();
        serviceResult.setMessage("lay tong so bai dang chua duyet thanh cong");
        serviceResult.setStatus(HttpStatus.OK);
        serviceResult.setData(output);
        return ResponseEntity.ok().body(serviceResult);
    }

    @GetMapping(path = "/count/da-duyet")
    public ResponseEntity<ServiceResult> countApproved(){
        int total = articleRepository.countArticleApproved();
        Map<String, Object> output = new HashMap<>();
        output.put("total", total);
        ServiceResult serviceResult = new ServiceResult<Map<String, Object>>();
        serviceResult.setMessage("lay tong so bai dang da duyet thanh cong");
        serviceResult.setStatus(HttpStatus.OK);
        serviceResult.setData(output);
        return ResponseEntity.ok().body(serviceResult);
    }

    @GetMapping(path = "/count/block")
    public ResponseEntity<ServiceResult> countArticleBlocked(){
        int total = articleRepository.countArticleBlocked();
        Map<String, Object> output = new HashMap<>();
        output.put("total", total);
        ServiceResult serviceResult = new ServiceResult<Map<String, Object>>();
        serviceResult.setMessage("lay tong so bai dang da khoa thanh cong");
        serviceResult.setStatus(HttpStatus.OK);
        serviceResult.setData(output);
        return ResponseEntity.ok().body(serviceResult);
    }

    //Search với phân trang mới nhất
    @PostMapping(path = "/user")
    public ResponseEntity<ServiceResult> ArticlesUser(@RequestBody PagingUserIdDTO pagingUserIdDTO) {
        log.debug("REST request to search Article by Searchoption: {}");
        ServiceResult<Map<String, Object>> result = articleService.getAllArticleByUserId(pagingUserIdDTO);
        return ResponseEntity.ok().body(result);
    }

    //Lấy một tin đăng theo article id và userid
    @GetMapping("/user-article/{userId}/{articleId}")
    public ResponseEntity<List<Map<String, Object>>> getArticleUser(@PathVariable Long userId, @PathVariable Long articleId) {
        List<Map<String, Object>> result = articleRepository.getArticleByUserId(userId, articleId);
        return ResponseEntity.ok().body(result);
    }

    @PutMapping("/delete")
    public ResponseEntity<ServiceResult> deleteArticle(@RequestBody Long id){
        return ResponseEntity.ok().body(articleService.delete(id));
    }

    //Search với phân trang đã được duyệt
    @PostMapping(path = "/search3/{id}")
    public ResponseEntity<ServiceResult> SearchArticleWithPagination3(@PathVariable("id") Long id, @RequestBody SearchDTO searchOption) {
        log.debug("REST request to search Article by Searchoption: {}");
        ServiceResult<Map<String, Object>> result = articleService.searchArticleWithPagination3(id, searchOption);
        return ResponseEntity.ok().body(result);
    }

    //Search với phân trang mới nhất
    @PostMapping(path = "/search-newest2/{id}")
    public ResponseEntity<ServiceResult> SearchArticleNewest2(@PathVariable("id") Long id, @RequestBody SearchDTO searchOption) {
        log.debug("REST request to search Article by Searchoption: {}");
        ServiceResult<Map<String, Object>> result = articleService.searchArticleWithNewsest2(id,searchOption);
        return ResponseEntity.ok().body(result);
    }
}
