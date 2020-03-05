package com.student.community.service;

import com.student.community.vo.Notification;

import java.util.List;

public interface INotificationService {
    /**
     * 插入一个通知
     * @param notification
     * @return
     */
    int insertNotification(Notification notification);

    /**
     * 根据接收者id查询该用户未读信息数
     * @param receiverId
     * @return
     */
    int selectUnReadNotifyCountByReceiverId(int receiverId);

    /**
     * 根据接收者id查询该用户未读信息
     * @param receiverId
     * @return
     */
    List<Notification> selectUnReadNotifyByReceiverId(int receiverId);

    /**
     * 根据Id将评论状态设置为已读
     * @param id
     * @return
     */
    int updateStatusById(int id);
}
