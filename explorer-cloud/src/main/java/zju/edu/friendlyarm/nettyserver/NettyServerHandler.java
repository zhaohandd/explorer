package zju.edu.friendlyarm.nettyserver;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @author xhzhao
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    private static Logger logger = LoggerFactory.getLogger(NettyServerHandler.class);

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        logger.info("Server Channel Active...");
        ByteBuf msg;
        String path = "E:\\LIFE_OF_ZJU\\IDS\\Kwon2017_Article_ASurveyOfDeepLearning-basedNet.pdf";
        InputStream in = new FileInputStream(path);
        byte[] buffer = new byte[1024 * 100];
        int n;
        while ((n = in.read(buffer)) != -1) {
            msg = Unpooled.buffer(buffer.length);
            msg.writeBytes(buffer, 0, n);
            ctx.writeAndFlush(msg);
            msg.clear();
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        logger.info("Server Send Msg...");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
