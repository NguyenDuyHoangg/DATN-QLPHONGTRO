package com.datn.hoang.services.impl;

import com.datn.hoang.entity.*;
import com.datn.hoang.repository.*;
import com.datn.hoang.services.ArticleService;
import com.datn.hoang.services.dto.*;
import com.datn.hoang.services.mapper.ArticleMapper;
import com.datn.hoang.services.mapper.ImageMapper;
import com.datn.hoang.services.mapper.LocationMapper;
import com.datn.hoang.services.mapper.RoomMapper;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {

    private final Logger log = LoggerFactory.getLogger(ArticleServiceImpl.class);

    private final RoomMapper roomMapper;
    private final ArticleMapper articleMapper;

    private final LocationMapper locationMapper;

    private final ImageMapper imageMapper;

    private final ArticleRepository articleRepository;

    private final RoomRepository roomRepository;

    private final ImageRepository imageRepository;

    private final LocationRepository locationRepository;

    private final  WardRepository wardRepository;

    private final UserRepository userRepository;

    public ArticleServiceImpl(RoomMapper roomMapper, ArticleMapper articleMapper, LocationMapper locationMapper, ImageMapper imageMapper, ArticleRepository articleRepository, RoomRepository roomRepository, ImageRepository imageRepository, LocationRepository locationRepository, WardRepository wardRepository, UserRepository userRepository) {
        this.roomMapper = roomMapper;
        this.articleMapper = articleMapper;
        this.locationMapper = locationMapper;
        this.imageMapper = imageMapper;
        this.articleRepository = articleRepository;
        this.roomRepository = roomRepository;
        this.imageRepository = imageRepository;
        this.locationRepository = locationRepository;
        this.wardRepository = wardRepository;
        this.userRepository = userRepository;
    }

    //Lấy tất cả bài viết có phân trang
    @Override
    public List<Map<String, Object>> getAllArticleNewest(){
        return articleRepository.getAllArticleNewest();
    }

    //Lấy bài viết theo id
    @Override
    public List<Map<String, Object>> getArticleById(Long id){
        return articleRepository.getArticleById(id);
    }

    //Lấy tất cả tin đăng theo trang thái bài viết ( chờ duyệt / duyệt )
    @Override
    public List<Map<String, Object>> getAllArticleWithApprovedStatus(Pageable page,Long approvedStatus){
        log.debug("REST request to get Article with approved status : {}", approvedStatus);
        return articleRepository.getAllArticleWithApprovedStatus(page, approvedStatus);
    }

    //Lấy tin đăng trong màn chờ duyệt
    @Override
    public List<Map<String, Object>> getAllArticleByNotApproved(Pageable page, Long articleStatus){
        log.debug("REST request to get Article with articleStatus : {}", articleStatus);
        return articleRepository.getAllArticleByNotApproved(page, articleStatus);
    }

    //Lấy tin đăng trong màn đã duyệt
    @Override
    public List<Map<String, Object>> getAllArticleByApproved(Pageable page, Long articleStatus, Long articleApproved){
        log.debug("REST request to get Article with articleStatus : {}", articleStatus);
        return articleRepository.getAllArticleByApproved(page, articleStatus, articleApproved);
    }

    //Lấy tất cả tin đăng đã bị khóa
    @Override
    public List<Map<String, Object>> getAllArticleBlocked(Pageable page){
        return articleRepository.getAllArticleBlocked(page);
    }

    //Duyệt bài đăng
    @Override
    public ServiceResult<ArticleDTO> ApproveArticle(Long id,Long articleApproved, Long articleStatus, Long articleNotApproved) {
        String messageErr = "";
        Article instanceArticle = articleRepository.findArticleById(id);
        if (instanceArticle == null) {
            messageErr += "Không tìm thấy bài đăng!";
            ServiceResult serviceResult = new ServiceResult<ArticleDTO>();
            serviceResult.setMessage(messageErr);
            serviceResult.setStatus(HttpStatus.NOT_FOUND);
            serviceResult.setData(instanceArticle);
            return serviceResult;
        }
        instanceArticle.setArticleApproved(articleApproved);
        instanceArticle.setArticleStatus(articleStatus);
        instanceArticle.setArticleNotApproved(articleNotApproved);
        // Lấy thời gian hiện tại
        Calendar calendar = Calendar.getInstance();
        Date currentDate = calendar.getTime();

        // Thêm 15 ngày
        calendar.add(Calendar.DATE, 15);
        Date futureDate = calendar.getTime();

        instanceArticle.setArticleExpirationDate(futureDate);
        articleRepository.save(instanceArticle);
        ServiceResult serviceResult = new ServiceResult<ArticleDTO>();
        serviceResult.setMessage("Cập nhật duyệt thành công");
        serviceResult.setStatus(HttpStatus.OK);
        serviceResult.setData(instanceArticle);
        return serviceResult;
    }

    //Không duyệt bài đăng
    @Override
    public ServiceResult<ArticleDTO> NotApproveArticle(Long id,Long articleApproved, Long articleStatus) {
        String messageErr = "";
        Article instanceArticle = articleRepository.findArticleById(id);
        if (instanceArticle == null) {
            messageErr += "Không tìm thấy bài đăng!";
            ServiceResult serviceResult = new ServiceResult<ArticleDTO>();
            serviceResult.setMessage(messageErr);
            serviceResult.setStatus(HttpStatus.NOT_FOUND);
            serviceResult.setData(instanceArticle);
            return serviceResult;
        }
        instanceArticle.setArticleApproved(articleApproved);
        instanceArticle.setArticleStatus(articleStatus);
        instanceArticle.setArticleNotApproved(1L);
        articleRepository.saveAndFlush(instanceArticle);
        ServiceResult serviceResult = new ServiceResult<ArticleDTO>();
        serviceResult.setMessage("Không duyệt bài đăng thành công");
        serviceResult.setStatus(HttpStatus.OK);
        serviceResult.setData(instanceArticle);
        return serviceResult;
    }

    //Khóa bài đăng
    @Override
    public ServiceResult<ArticleDTO> BlockArticle(Long id,Long articleBlocked) {
        String messageErr = "";
        Article instanceArticle = articleRepository.findArticleById(id);
        if (instanceArticle == null) {
            messageErr += "Không tìm thấy bài đăng!";
            ServiceResult serviceResult = new ServiceResult<ArticleDTO>();
            serviceResult.setMessage(messageErr);
            serviceResult.setStatus(HttpStatus.NOT_FOUND);
            serviceResult.setData(instanceArticle);
            return serviceResult;
        }
        if(articleBlocked==0){
            // Lấy thời gian hiện tại
            Calendar calendar = Calendar.getInstance();
            Date currentDate = calendar.getTime();

            // Thêm 15 ngày
            calendar.add(Calendar.DATE, 15);
            Date futureDate = calendar.getTime();

            instanceArticle.setArticleExpirationDate(futureDate);
        }
        instanceArticle.setArticleBlocked(articleBlocked);
        articleRepository.saveAndFlush(instanceArticle);
        ServiceResult serviceResult = new ServiceResult<ArticleDTO>();
        serviceResult.setMessage("Khóa bài đăng thành công");
        serviceResult.setStatus(HttpStatus.OK);
        serviceResult.setData(instanceArticle);
        return serviceResult;
    }

    @Override
    public ServiceResult<Map<String, Object>> searchArticleWithPagination(SearchDTO searchOption) {
        this.log.debug("search map subjects with pagination");
        ServiceResult serviceResult = new ServiceResult<Map<String, Object>>();

        try {
            //Tạo  size, page mặc định nếu trong searchOption không có
            Integer size = 5;
            Integer page = 0;

            if (searchOption.getSize() != null) {
                size = searchOption.getSize();
            }

            if (searchOption.getPage() != null) {
                page = searchOption.getPage();
                page--;
            }

            Pageable pageRequest = PageRequest.of(page, size);

            // Xử lý lại các otption trước khi tìm kiếm
            searchOption.handleBeforeSearch();

            List<Map<String, Object>> pageUI = articleRepository.searchArticleWithPagination(pageRequest, searchOption);
            int total = articleRepository.countAllArticleWithPagination(searchOption);

            Map<String, Object> output = new HashMap<>();
            output.put("total", total);
            output.put("size", pageRequest.getPageSize());
            output.put("page", pageRequest.getPageNumber() + 1);
            output.put("articles", pageUI);

            serviceResult.setMessage("Tim kiem thanh cong");
            serviceResult.setStatus(HttpStatus.OK);
            serviceResult.setData(output);
        } catch (Exception e) {
            this.log.error(e.getMessage(), e);
            serviceResult.setMessage("Tim kiem that bai");
            serviceResult.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Map<String, Object>> searchArticleWithPagination2(SearchDTO searchOption) {
        this.log.debug("search map subjects with pagination");
        ServiceResult serviceResult = new ServiceResult<Map<String, Object>>();

        try {
            //Tạo  size, page mặc định nếu trong searchOption không có
            Integer size = 5;
            Integer page = 0;

            if (searchOption.getSize() != null) {
                size = searchOption.getSize();
            }

            if (searchOption.getPage() != null) {
                page = searchOption.getPage();
                page--;
            }

            Pageable pageRequest = PageRequest.of(page, size);

            // Xử lý lại các otption trước khi tìm kiếm
            searchOption.handleBeforeSearch();

            List<Map<String, Object>> pageUI = articleRepository.searchArticleWithPagination2(pageRequest, searchOption);
            int total = articleRepository.countAllArticleWithPagination2(searchOption);

            Map<String, Object> output = new HashMap<>();
            output.put("total", total);
            output.put("size", pageRequest.getPageSize());
            output.put("page", pageRequest.getPageNumber() + 1);
            output.put("articles", pageUI);

            serviceResult.setMessage("Tim kiem thanh cong");
            serviceResult.setStatus(HttpStatus.OK);
            serviceResult.setData(output);
        } catch (Exception e) {
            this.log.error(e.getMessage(), e);
            serviceResult.setMessage("Tim kiem that bai");
            serviceResult.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Map<String, Object>> searchArticleWithNewsest(SearchDTO searchOption) {
        this.log.debug("search map subjects with article newest");
        ServiceResult serviceResult = new ServiceResult<Map<String, Object>>();

        try {
            //Tạo  size, page mặc định nếu trong searchOption không có
            Integer size = 5;
            Integer page = 0;

            if (searchOption.getSize() != null) {
                size = searchOption.getSize();
            }

            if (searchOption.getPage() != null) {
                page = searchOption.getPage();
                page--;
            }

            Pageable pageRequest = PageRequest.of(page, size);

            // Xử lý lại các otption trước khi tìm kiếm
            searchOption.handleBeforeSearch();

            List<Map<String, Object>> pageUI = articleRepository.searchArticleNewest(pageRequest, searchOption);
            int total = articleRepository.countAllArticleWithPagination2(searchOption);

            Map<String, Object> output = new HashMap<>();
            output.put("total", total);
            output.put("size", pageRequest.getPageSize());
            output.put("page", pageRequest.getPageNumber() + 1);
            output.put("articles", pageUI);

            serviceResult.setMessage("Tim kiem thanh cong");
            serviceResult.setStatus(HttpStatus.OK);
            serviceResult.setData(output);
        } catch (Exception e) {
            this.log.error(e.getMessage(), e);
            serviceResult.setMessage("Tim kiem that bai");
            serviceResult.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<RoomDTO> create(RoomDTO roomDTO){
        Location location = locationMapper.toEntity(roomDTO.getLocationDTO());
        location = locationRepository.saveAndFlush(location);
        Room room = roomMapper.toEntity(roomDTO);
        room.setRoomLocationId(location.getLocationId());
        roomRepository.saveAndFlush(room);
        Article article = articleMapper.toEntity(roomDTO.getArticleDTO());
        article.setArticleNotApproved(0L);
        article.setArticleBlocked(0L);
        article.setArticleStatus(0L);
        article.setArticleApproved(0L);
        article.setArticleCreatedDate(new Date());
        article.setArticleRoomId(room.getRoomId());
        article = articleRepository.saveAndFlush(article);

//        List<Image> lstImage = roomDTO.getLstImageDTO().stream().map(imageMapper::toEntity).collect(Collectors.toList());
//        lstImage.forEach(e -> imageRepository.saveAndFlush(e));

        Long id = article.getArticleId();
        roomDTO.setArticleDTO(articleMapper.toDto(article));

        //      Lưu danh sách vào CSDL
        ServiceResult serviceResult = new ServiceResult<List<Map<String, Object>>>();
        serviceResult.setMessage("Them moi thanh cong");
        serviceResult.setStatus(HttpStatus.OK);
        serviceResult.setData(roomDTO);

        return serviceResult;
    }

    @Override
    public ServiceResult<RoomDTO> update(ArticleUpdateDTO articleUpdateDTO) throws EntityNotFoundException {
        Location location = locationRepository.findById(articleUpdateDTO.getLocationId()).orElseThrow(EntityNotFoundException::new);
        location.setLocationAddress(articleUpdateDTO.getLocationAddress());
        location.setLocationWardCode(articleUpdateDTO.getLocationWardCode());

        Article article = articleRepository.findArticleById(articleUpdateDTO.getArticleId());
        article.setArticleTitle(articleUpdateDTO.getArticleTitle());
        article.setArticleSummary(articleUpdateDTO.getArticleSummary());
        article.setArticleContent(articleUpdateDTO.getArticleContent());
        article.setArticleApproved(articleUpdateDTO.getArticleApproved());
        article.setArticleStatus(articleUpdateDTO.getArticleStatus());
        article.setArticleLastModified(new Date());
        article.setArticleBlocked(articleUpdateDTO.getArticleBlocked());
        article.setArticleNotApproved(articleUpdateDTO.getArticleNotApproved());
        // Lấy thời gian hiện tại
        Calendar calendar = Calendar.getInstance();
        Date currentDate = calendar.getTime();

        // Thêm 15 ngày
        calendar.add(Calendar.DATE, 15);
        Date futureDate = calendar.getTime();
        article.setArticleExpirationDate(futureDate);

        Room room = roomRepository.findById(articleUpdateDTO.getRoomId()).orElseThrow(EntityNotFoundException::new);
        room.setRoomAcreage(articleUpdateDTO.getRoomAcreage());
        room.setRoomAirconditioner(articleUpdateDTO.getRoomAirconditioner());
        room.setRoomHeater(articleUpdateDTO.getRoomHeater());
        room.setRoomElectricPrice(articleUpdateDTO.getRoomElectricPrice());
        room.setRoomEnviromentPrice(articleUpdateDTO.getRoomEnviromentPrice());
        room.setRoomPrice(articleUpdateDTO.getRoomPrice());
        room.setRoomStatus(articleUpdateDTO.getRoomStatus());
        room.setRoomWaterPrice(articleUpdateDTO.getRoomWaterPrice());
        room.setRoomInternet(articleUpdateDTO.getRoomInternet());
        room.setRoomParking(articleUpdateDTO.getRoomParking());
        room.setRoomTelevision(articleUpdateDTO.getRoomTelevision());
        room.setRoomToilet(articleUpdateDTO.getRoomToilet());
        room.setRoomInternetPrice(articleUpdateDTO.getRoomInternetPrice());
        room.setRoomRoomTypeId(articleUpdateDTO.getRoomRoomTypeId());

        Wards wards = wardRepository.findWardsByCode(articleUpdateDTO.getLocationWardCode());
        wards.setDistrictCode(articleUpdateDTO.getDistrictCode());

        articleUpdateDTO.setArticleExpirationDate(article.getArticleExpirationDate());

        //      Lưu danh sách vào CSDL
        ServiceResult serviceResult = new ServiceResult<List<Map<String, Object>>>();
        serviceResult.setMessage("Cập nhật thành công!");
        serviceResult.setStatus(HttpStatus.OK);
        articleUpdateDTO.setArticleLastModified(article.getArticleLastModified());
        serviceResult.setData(articleUpdateDTO);

        return serviceResult;
    }

    @Override
    public ServiceResult<Map<String, Object>> getAllArticleByUserId(PagingUserIdDTO pagingUserIdDTO){
        ServiceResult serviceResult = new ServiceResult<Map<String, Object>>();

        try {
            //Tạo  size, page mặc định nếu trong searchOption không có
            Integer size = 5;
            Integer page = 0;

            if (pagingUserIdDTO.getSize() != null) {
                size = pagingUserIdDTO.getSize();
            }

            if (pagingUserIdDTO.getPage() != null) {
                page = pagingUserIdDTO.getPage();
                page--;
            }

            Pageable pageRequest = PageRequest.of(page, size);


            List<Map<String, Object>> pageUI = articleRepository.getAllArticleByUserId(pageRequest, pagingUserIdDTO.getUserId());
            int total = articleRepository.countArticleUserId(pagingUserIdDTO.getUserId());

            Map<String, Object> output = new HashMap<>();
            output.put("total", total);
            output.put("size", pageRequest.getPageSize());
            output.put("page", pageRequest.getPageNumber() + 1);
            output.put("articlesUser", pageUI);

            serviceResult.setMessage("Lay bai dang theo nguoi dung thanh cong");
            serviceResult.setStatus(HttpStatus.OK);
            serviceResult.setData(output);
        } catch (Exception e) {
            this.log.error(e.getMessage(), e);
            serviceResult.setMessage("Lay bai dang theo nguoi dung that bai");
            serviceResult.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<ArticleDTO> delete(Long id){
        Article article = articleRepository.findArticleById(id);
        article.setArticleNotApproved(1L);
        articleRepository.save(article);
        ArticleDTO articleDTO = articleMapper.toDto(article);
        return new ServiceResult<>(articleDTO, HttpStatus.OK, "Xoa bai dang thanh cong!");
    }

    @Override
    public ServiceResult<Map<String, Object>> searchArticleWithPagination3(Long id, SearchDTO searchOption) {
        this.log.debug("search map subjects with pagination");
        ServiceResult serviceResult = new ServiceResult<Map<String, Object>>();

        try {
            //Tạo  size, page mặc định nếu trong searchOption không có
            Integer size = 5;
            Integer page = 0;

            if (searchOption.getSize() != null) {
                size = searchOption.getSize();
            }

            if (searchOption.getPage() != null) {
                page = searchOption.getPage();
                page--;
            }

            Pageable pageRequest = PageRequest.of(page, size);

            // Xử lý lại các otption trước khi tìm kiếm
            searchOption.handleBeforeSearch();

            List<Map<String, Object>> pageUI = articleRepository.searchArticleWithPagination3(pageRequest,id, searchOption);
            int total = articleRepository.countAllArticleWithPagination3(id, searchOption);

            Map<String, Object> output = new HashMap<>();
            output.put("total", total);
            output.put("size", pageRequest.getPageSize());
            output.put("page", pageRequest.getPageNumber() + 1);
            output.put("articles", pageUI);

            serviceResult.setMessage("Tim kiem thanh cong");
            serviceResult.setStatus(HttpStatus.OK);
            serviceResult.setData(output);
        } catch (Exception e) {
            this.log.error(e.getMessage(), e);
            serviceResult.setMessage("Tim kiem that bai");
            serviceResult.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Map<String, Object>> searchArticleWithNewsest2(Long id, SearchDTO searchOption) {
        this.log.debug("search map subjects with article newest");
        ServiceResult serviceResult = new ServiceResult<Map<String, Object>>();

        try {
            //Tạo  size, page mặc định nếu trong searchOption không có
            Integer size = 5;
            Integer page = 0;

            if (searchOption.getSize() != null) {
                size = searchOption.getSize();
            }

            if (searchOption.getPage() != null) {
                page = searchOption.getPage();
                page--;
            }

            Pageable pageRequest = PageRequest.of(page, size);

            // Xử lý lại các otption trước khi tìm kiếm
            searchOption.handleBeforeSearch();

            List<Map<String, Object>> pageUI = articleRepository.searchArticleNewest2(pageRequest, id, searchOption);
            int total = articleRepository.countAllArticleWithPagination3(id, searchOption);

            Map<String, Object> output = new HashMap<>();
            output.put("total", total);
            output.put("size", pageRequest.getPageSize());
            output.put("page", pageRequest.getPageNumber() + 1);
            output.put("articles", pageUI);

            serviceResult.setMessage("Tim kiem thanh cong");
            serviceResult.setStatus(HttpStatus.OK);
            serviceResult.setData(output);
        } catch (Exception e) {
            this.log.error(e.getMessage(), e);
            serviceResult.setMessage("Tim kiem that bai");
            serviceResult.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return serviceResult;
    }
}
