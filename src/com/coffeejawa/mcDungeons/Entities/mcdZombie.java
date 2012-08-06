package com.coffeejawa.mcDungeons.Entities;

import net.minecraft.server.EntityCreature;
import net.minecraft.server.Navigation;
import net.minecraft.server.World;

import org.bukkit.Location;
import org.bukkit.craftbukkit.entity.CraftCreature;
import org.bukkit.entity.Zombie;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityEvent;

import com.coffeejawa.mcDungeons.Entities.ZombieMoveEvent;

public class mcdZombie extends net.minecraft.server.EntityZombie{

    private float speed;
    
    public mcdZombie(World world) {
        super(world);
        speed = 0.1f;
    }
    
    public void F_(){
        Zombie zombie = (Zombie) this.getBukkitEntity();
       
        Location from = new Location(zombie.getWorld(), this.lastX, this.lastY, this.lastZ, this.lastYaw, this.lastPitch);
        Location to = new Location(zombie.getWorld(), this.locX, this.locY, this.locZ, this.yaw, this.pitch);
        EntityCreature ec = ((CraftCreature)zombie).getHandle();
        Navigation nav = ec.al();
          nav.a(this.speed);
          
        ZombieMoveEvent event = new ZombieMoveEvent(zombie, from, to, nav);
        if (!event.isCancelled() && !zombie.isDead()){
            this.world.getServer().getPluginManager().callEvent(event);
        }
        
        super.F_();
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }
    
    
    
}