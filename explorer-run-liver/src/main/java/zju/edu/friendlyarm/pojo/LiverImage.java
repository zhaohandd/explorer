package zju.edu.friendlyarm.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel(description = "image资源")
public class LiverImage {

    @NotNull
    private Integer id;

    @ApiModelProperty(value = "医生ID账号")
    @NotNull
    private Double doctorNum;

    @ApiModelProperty(value = "病人ID账号")
    @NotNull
    private Double patientNum;

    @ApiModelProperty(value = "图片相对路径，文件夹使用“/”分割")
    @NotNull
    private String ralativePath;

    @ApiModelProperty(value = "图片名称")
    private String imageName;

}