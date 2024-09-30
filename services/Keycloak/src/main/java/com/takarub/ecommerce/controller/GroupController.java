package com.takarub.ecommerce.controller;

import com.takarub.ecommerce.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/groups")
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;

    @PutMapping("/{groupId}/assign/users/{userId}")
    public ResponseEntity<?> AssignUserToGroup(@PathVariable String userId,
                                               @PathVariable String groupId ) {
        groupService.assignGroup(userId, groupId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{groupId}/assign/users/{userId}")
    public ResponseEntity<?> UnAssignUserToGroup(@PathVariable String userId,
                                                 @PathVariable String groupId ) {
        groupService.deleteGroup(userId, groupId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
