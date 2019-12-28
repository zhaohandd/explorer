package zju.edu.friendlyarm.nettyclient;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author xhzhao
 */
public class NettyClientHandler extends ChannelInboundHandlerAdapter {

    private static Logger logger = LoggerFactory.getLogger(NettyClientHandler.class);

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        logger.info("Client Channel Active...");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        logger.info("Client Receiving Msg: {}", msg);
        String path = "E:\\LIFE_OF_ZJU\\Kwon2017_Article_ASurveyOfDeepLearning-basedNet.pdf";
        File file = new File(path);
        if (!file.exists() && !file.createNewFile()) {
            throw new IOException();
        }
        FileOutputStream fos = new FileOutputStream(file, true);
        ByteBuf buf = (ByteBuf) msg;
        byte[] bytes = new byte[buf.readableBytes()];
        buf.readBytes(bytes);
        logger.info("client receives content length: {}", bytes.length);
        fos.write(bytes);
        fos.flush();
        fos.close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
