package com.priceflow.service;

import com.priceflow.mapper.NoticeMapper;
import com.priceflow.pojo.dto.NoticeDTO;
import com.priceflow.pojo.entity.Notice;
import com.priceflow.pojo.vo.Result;
import com.priceflow.utils.UserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author 33954
 * #Description NoticeService
 * #Date: 2025/3/30 19:06
 */
@Service
public class NoticeService {

    @Autowired
    private NoticeMapper noticeMapper;

    public Result addNotice(NoticeDTO noticeDTO) {
        Notice notice = tranfrom(noticeDTO);
        noticeMapper.insert(notice);
        return Result.success("设置成功");
    }

    private Notice tranfrom(NoticeDTO noticeDTO) {
        Notice notice = new Notice();
        notice.setUserId(UserHolder.getUser());
        notice.setDescription(noticeDTO.getDescription());
        notice.setPresentPrice(noticeDTO.getPrice());
        notice.setTargetPrice(noticeDTO.getTargetPrice());
        notice.setShopName(noticeDTO.getShopName());
        notice.setImg(noticeDTO.getImg());
        notice.setIsNotice(false);
        return notice;
    }

    public Result cancelNotice(Integer id) {
        noticeMapper.deleteById(id);
        return Result.success("取消成功");
    }

    public Result modifyNotice(Integer id, BigDecimal targetPrice) {
        noticeMapper.updateTargetPrice(id, targetPrice);
        return Result.success("修改成功");
    }

    public Result<List<Notice>> getNotices() {
        return Result.success(noticeMapper.selectByUserId(UserHolder.getUser()));
    }
}
