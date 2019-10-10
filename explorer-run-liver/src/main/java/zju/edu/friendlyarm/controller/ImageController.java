package zju.edu.friendlyarm.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import zju.edu.friendlyarm.mapper.LiverImageMapper;
import zju.edu.friendlyarm.pojo.LiverImage;
import zju.edu.friendlyarm.service.ImageService;
import zju.edu.friendlyarm.util.FileAccessHelper;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author xhzhao
 */
@Validated
@RestController
@RequestMapping("/api/run/image")
@Api(tags = "CT图片接口")
public class ImageController {

    @Autowired
    private ImageService imageService;
    @Autowired
    private LiverImageMapper imageMapper;
    @Autowired
    private FileAccessHelper fileAccessHelper;


    @ApiOperation("图片文件上传")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Integer createOrUpdate(@RequestParam @NotNull Double doctorNum, @RequestParam @NotNull Double patientNum, @RequestPart @NotEmpty MultipartFile file) throws IOException {
        return imageService.createOrUpdate(doctorNum, patientNum, file);

    }

    @ApiOperation("模型运行")
    @PostMapping("run")
    public boolean runModel(String imageName) {
        imageService.run(imageName);
        return true;
    }

    @ApiOperation("运行返回的图片http地址")
    @GetMapping("{id}/url")
    public String getImageUrl(@PathVariable Integer id) {
        LiverImage image = imageMapper.selectByPrimaryKey(id);
        if (image != null && image.getRelativePath() != null) {
            return fileAccessHelper.buildHttpUrl(image.getRelativePath(), image.getUpdateAt());
        }
        return null;
    }

    @ApiOperation("查询历史图片http地址")
    @GetMapping("{doctorNum}/{patientNum}")
    public List<String> getImageUrls(@PathVariable Double doctorNum, @PathVariable Double patientNum) {
        return null;
    }
}
