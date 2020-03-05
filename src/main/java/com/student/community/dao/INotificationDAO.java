package com.student.community.dao;

import com.student.community.vo.Notification;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface INotificationDAO {
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
    int selectUnReadNotifyCountByReceiverId(@Param("receiverId") int receiverId);

    /**
     * 根据接收者id查询该用户未读信息
     * @param receiverId
     * @return
     */
    List<Notification> selectUnReadNotifyByReceiverId(@Param("receiverId") int receiverId);

    /**
     * 根据Id将评论状态设置为已读
     * @param id
     * @return
     */
    int updateStatusById(@Param("id") int id);
}
