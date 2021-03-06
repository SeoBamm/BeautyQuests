package fr.skytasul.quests.utils.nms;

import org.apache.commons.lang.Validate;
import org.bukkit.craftbukkit.v1_14_R1.CraftParticle;
import org.bukkit.craftbukkit.v1_14_R1.entity.CraftPlayer;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import fr.skytasul.quests.utils.ParticleEffect;
import io.netty.buffer.ByteBuf;
import net.minecraft.server.v1_14_R1.ChatComponentText;
import net.minecraft.server.v1_14_R1.EnumChatFormat;
import net.minecraft.server.v1_14_R1.EnumHand;
import net.minecraft.server.v1_14_R1.Packet;
import net.minecraft.server.v1_14_R1.PacketPlayOutOpenBook;
import net.minecraft.server.v1_14_R1.PacketPlayOutWorldParticles;

public class v1_14_R1 extends NMS{
	
	public Object bookPacket(ByteBuf buf){
		return new PacketPlayOutOpenBook(EnumHand.MAIN_HAND);
	}

	public Object worldParticlePacket(ParticleEffect effect, boolean paramBoolean, float paramFloat1, float paramFloat2,
			float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, float paramFloat7, int paramInt,
			Object paramData) {
	return new PacketPlayOutWorldParticles(CraftParticle.toNMS(effect.getBukkitParticle(), paramData), paramBoolean, paramFloat1, paramFloat2, paramFloat3, paramFloat4, paramFloat5, paramFloat6, paramFloat7, paramInt);
	}
	
	public void sendPacket(Player p, Object packet){
		Validate.isTrue(packet instanceof Packet, "The object specified is not a packet.");
		((CraftPlayer) p).getHandle().playerConnection.sendPacket((Packet<?>) packet);
	}

	public double entityNameplateHeight(LivingEntity en){
		return en.getHeight();
	}
	
	public Object getIChatBaseComponent(String text){
		return new ChatComponentText(text);
	}

	public Object getEnumChatFormat(int value){
		return EnumChatFormat.a(value);
	}
	
}