package Employee.project.Employee.Utility;

import Employee.project.Employee.Response.BaseResponse;
import Employee.project.Employee.Response.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EmployeeUtility {
    public static Result getResult() {
        Result result = new Result();
        result.setResponseCode(0);
        result.setResponseDescription("Success");
        return result;
    }

    public static Result getFailureResult(int responseCode, String responseDescription) {
        Result result = new Result();
        result.setResponseCode(responseCode);
        result.setResponseDescription(responseDescription);
        return result;
    }

    public static BaseResponse baseResponseResult() {
        BaseResponse response = new BaseResponse();
        response.setStatus(200);
        response.setResult(getResult());
        return response;
    }

    public static BaseResponse baseResponseFailResult() {
        BaseResponse response = new BaseResponse();
        response.setResult(getFailureResult(400, "Bad Request"));
        return response;
    }

    public static BaseResponse getBaseResponse(Object data) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(data);
        baseResponse.setStatus(200);
        baseResponse.setResult(getResult());
        return baseResponse;
    }
}
