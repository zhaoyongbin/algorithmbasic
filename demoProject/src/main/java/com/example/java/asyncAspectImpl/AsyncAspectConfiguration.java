package com.example.java.asyncAspectImpl;

import com.example.java.asyncAspect.process.ExecuteResultStorager;
import com.example.java.asyncAspect.process.StakeResultProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AsyncAspectConfiguration {
    public StakeResultProcessor stakeResultProcessor(){
        StakeResultProcessor<ReturnMsg> stakeResultProcessor =new BipStakeResultProcessor();
        return stakeResultProcessor;
    }

    @Bean
    public ExecuteResultStorager executeResultStorager(){
        ExecuteResultStorager executeResultStorager=new ExecuteResultRedisStorager ("BIP-INDEXOM-ASYNC");
//        ExecuteResultStorager executeResultStorager=new ExecuteResultRedisStorager();
        return executeResultStorager;
    }
}
