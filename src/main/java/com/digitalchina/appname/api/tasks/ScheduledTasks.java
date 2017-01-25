package com.digitalchina.appname.api.tasks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    //@Scheduled(cron = "0 0 16 * * ?")
//    @Scheduled(fixedRate = 30000)
//    public void reportCurrentTime() {
//    	// 请注意：需要保证在计划的task没有运行时，如何重新运行
//        log.info("########## The task executed at {}", new SimpleDateFormat("HH:mm:ss").format(new Date()));
//    }
}
