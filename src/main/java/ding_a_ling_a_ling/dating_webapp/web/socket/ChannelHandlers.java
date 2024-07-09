package ding_a_ling_a_ling.dating_webapp.web.socket;

public final class ChannelHandlers {

  public static String getPattern(ChannelHandler channelHandler) {
    if (!"".equals(channelHandler.pattern())) {
      return channelHandler.pattern();
    }
    return channelHandler.value();
  }
}
