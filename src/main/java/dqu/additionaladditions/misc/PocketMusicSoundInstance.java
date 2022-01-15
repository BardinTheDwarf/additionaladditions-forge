package dqu.additionaladditions.misc;

import dqu.additionaladditions.config.Config;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class PocketMusicSoundInstance extends AbstractTickableSoundInstance {
    private final Player playerEntity;
    private final ItemStack stack;
    public static PocketMusicSoundInstance instance;

    public PocketMusicSoundInstance(SoundEvent soundEvent, Player playerEntity, ItemStack stack, boolean repeat, float volume) {
        super(soundEvent, SoundSource.RECORDS);
        this.playerEntity = playerEntity;
        this.stack = stack;
        this.looping = repeat;
        this.volume = volume;
        this.x = this.playerEntity.getX();
        this.y = this.playerEntity.getY();
        this.z = this.playerEntity.getZ();
    }

    @Override
    public void tick() {
        if (this.playerEntity.isDeadOrDying() || !this.playerEntity.getInventory().contains(stack) || !Config.get("PocketJukebox")) {
            this.stop();
        } else {
            this.x = this.playerEntity.getX();
            this.y = this.playerEntity.getY();
            this.z = this.playerEntity.getZ();
        }
    }

    public void finish() {
        this.stop();
    }

    public void play() {
        Minecraft.getInstance().getSoundManager().play(this);
    }
 }