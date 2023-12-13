package com.foodymoody.be.id_test;

import com.foodymoody.be.common.annotation.LoginId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.common.util.ids.ReplyId;
import com.foodymoody.be.common.util.ids.TasteMoodId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IdBindingTestController {

    @GetMapping("/binding-test/{pathId}")
    public ResponseEntity<IdBindingTestResponse> test(@PathVariable TasteMoodId pathId,
            @LoginId MemberId annotationId,
            @RequestParam ReplyId paramId,
            @RequestBody IdBindingTestRequest request) {
        System.out.printf("pathId - value : %s / type : %s\n", pathId.getValue(), pathId.getClass());
        System.out.printf("annotationId - value : %s / type : %s\n", annotationId.getValue(), annotationId.getClass());
        System.out.printf("paramId - value : %s / type : %s\n", paramId.getValue(), pathId.getClass());
        System.out.println(request);
        IdBindingTestResponse response = new IdBindingTestResponse(
                request.getMemberId(),
                request.getFeedId(),
                request.getCommentId(),
                pathId,
                annotationId,
                paramId);
        return ResponseEntity.ok(response);
    }

}
