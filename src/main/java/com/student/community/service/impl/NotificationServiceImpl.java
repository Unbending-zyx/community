package com.student.community.service.impl;

import com.student.community.dao.INotificationDAO;
import com.student.community.service.INotificationService;
import com.student.community.vo.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("notificationService")
public class NotificationServiceImpl implements INotificationService {

    @Autowired
    private INotificationDAO notificationDAO;

    @Override
    public int insertNotification(Notification notification) {
        return notificationDAO.insertNotification(notification);
    }

    @Override
    public int selectUnReadNotifyCountByReceiverId(int receiverId) {
        return notificationDAO.selectUnReadNotifyCountByReceiverId(receiverId);
    }

    @Override
    public List<Notification> selectUnReadNotifyByReceiverId(int receiverId) {
        return notificationDAO.selectUnReadNotifyByReceiverId(receiverId);
    }

    @Override
    public int updateStatusById(int id) {
        return notificationDAO.updateStatusById(id);
    }
}
