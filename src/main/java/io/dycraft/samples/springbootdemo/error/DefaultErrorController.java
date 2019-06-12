package io.dycraft.samples.springbootdemo.error;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Dayang Li on 12/06/2019
 */
@RestController
public class DefaultErrorController extends AbstractErrorController {

    private boolean DEBUG = true;

    public DefaultErrorController(ErrorAttributes errorAttributes) {
        super(errorAttributes);
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }

    @RequestMapping("/error")
    public ErrorResponse error(HttpServletRequest request) {
        Map<String, Object> body = getErrorAttributes(request, true);
        ErrorResponse errorResponse = new ErrorResponse(ErrorCode.ERROR, body.get("message").toString());
        if (DEBUG) {
            errorResponse.setMessage(body.get("message").toString());
            Map<String, Object> data = new HashMap<>();
            data.put("trace", body.get("trace"));
            errorResponse.setData(data);
        } else {
            if (getStatus(request) == HttpStatus.INTERNAL_SERVER_ERROR) {
                // Do not expose internal error info in production env
                errorResponse.setMessage("Server error");
            }
        }
        return errorResponse;
    }
}
