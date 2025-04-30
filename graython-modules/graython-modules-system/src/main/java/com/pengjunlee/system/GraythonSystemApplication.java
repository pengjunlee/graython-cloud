package com.pengjunlee.system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.pengjunlee.common.security.annotation.EnableCustomConfig;
import com.pengjunlee.common.security.annotation.EnableRyFeignClients;

/**
 * 系统模块
 * 
 * @author graython
 */
@EnableCustomConfig
@EnableRyFeignClients
@SpringBootApplication
@MapperScan("com.pengjunlee.system.mapper")  // 指定精确包路径
public class GraythonSystemApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(GraythonSystemApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  系统模块启动成功   ლ(´ڡ`ლ)ﾞ  \n" +
                " .-------.       ____     __        \n" +
                " |  _ _   \\      \\   \\   /  /    \n" +
                " | ( ' )  |       \\  _. /  '       \n" +
                " |(_ o _) /        _( )_ .'         \n" +
                " | (_,_).' __  ___(_ o _)'          \n" +
                " |  |\\ \\  |  ||   |(_,_)'         \n" +
                " |  | \\ `'   /|   `-'  /           \n" +
                " |  |  \\    /  \\      /           \n" +
                " ''-'   `'-'    `-..-'              ");
    }
}
