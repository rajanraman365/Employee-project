package Employee.project.Employee.Response;

import lombok.Data;

@Data
public class BaseResponse {
    private Integer status;
    private Object data;
    private Result result;

}
