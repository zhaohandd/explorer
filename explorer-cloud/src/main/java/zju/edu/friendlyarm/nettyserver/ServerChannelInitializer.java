package zju.edu.friendlyarm.nettyserver;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import zju.edu.friendlyarm.nettyclient.NettyClientHandler;

/**
 * @author xhzhao
 */
public class ServerChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        socketChannel.pipeline().addLast(new NettyServerHandler());
    }
}
