package com.jxmall.jxmall.controller;


import com.jxmall.jxmall.modle.Receiver;
import com.jxmall.jxmall.service.ReceiverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/receiver")
public class ReceiverController {
    @Autowired
    ReceiverService receiverService;

    @GetMapping(value = "/list/userId={userId}")
    public List<Receiver> getReceiverList(@PathVariable("userId") Integer userId){
        return receiverService.findAllByUserId(userId);
    }


    @PostMapping(value = "/add")
    public Receiver addReceiver(@RequestBody Receiver receiver)
    {
        return receiverService.save(receiver);

    }

    @PutMapping(value = "/updReceiver")
    public Receiver updateReceiver(@RequestBody Receiver receiver)
    {
        Receiver new_receiver = receiverService.getReceiverOne(receiver.getUserId(), receiver.getReceiverId());
        new_receiver.setReceiverName(receiver.getReceiverName());
        new_receiver.setReceiverPhone(receiver.getReceiverPhone());
        new_receiver.setReceiverAddressInfo(receiver.getReceiverAddressInfo());
        return receiverService.save(new_receiver);
    }

    @DeleteMapping(value = "/delReceiver/userId={userId}&receiverId={receiverId}")
    public void deleteReceiver(@PathVariable("userId") Integer userId,
                               @PathVariable("receiverId") Integer receiverId)
    {
        Receiver receiver = new Receiver();
        receiver = receiverService.getReceiverOne(userId, receiverId);
        receiverService.delete(receiver);
    }
}
