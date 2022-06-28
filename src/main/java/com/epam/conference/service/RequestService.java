package com.epam.conference.service;

import com.epam.conference.entity.dto.ReportDto;
import com.epam.conference.entity.event.Report;
import com.epam.conference.entity.event.RequestType;
import com.epam.conference.entity.event.UserRequest;
import com.epam.conference.entity.user.User;
import com.epam.conference.repository.EventRepository;
import com.epam.conference.repository.ReportRepository;
import com.epam.conference.repository.RequestRepository;
import com.epam.conference.repository.UserRepository;
import org.apache.coyote.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RequestService {
    @Autowired
    private RequestRepository requestRepository;
    @Autowired
    private ReportRepository reportRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EventRepository eventRepository;

    public UserRequest addNewRequest(UserRequest userRequest){
        requestRepository.save(userRequest);
        return userRequest;
    }
    public void requestForNewReport(Long requestId){
        Report report = requestRepository.getById(requestId).getReport();
        report.setAccepted(true);
        reportRepository.save(report);
        requestRepository.deleteById(requestId);
    }

    public List<Long> getListOfReqId(User user) {
        List<UserRequest> list = requestRepository.getByUserId(user.getId());
        List<Long> result = new ArrayList<>();
        for (UserRequest r:list) {
            if(r.getType() == RequestType.New_Speaker){
                result.add(r.getReport().getId());
            }
        }
        return result;
    }

    public List<UserRequest> getAllRequest() {
        return requestRepository.findAll();
    }


    public void accept(Long requestId) {
        UserRequest request = requestRepository.getById(requestId);
        if(request.getType() == RequestType.New_Report){
            requestForNewReport(requestId);
        }
        if(request.getType() == RequestType.New_Speaker){
            requestForNewSpeaker(requestId);
        }
    }
    
    public void reject(Long requestId){
        requestRepository.deleteById(requestId);
    }
    
    private void requestForNewSpeaker(Long requestId) {
        Report report = requestRepository.getById(requestId).getReport();
        report.setSpeaker(requestRepository.getById(requestId).getUser());
        reportRepository.save(report);
        requestRepository.deleteById(requestId);
    }
}
