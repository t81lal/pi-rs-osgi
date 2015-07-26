package org.nullbool.piexternal.game.api.accessors.task;

import java.awt.EventQueue;
import java.net.URL;
import org.nullbool.piexternal.game.api.accessors.task.ITask;

public interface ITaskHandler {
   EventQueue getEventQueue();

   void setEventQueue(EventQueue var1);

   ITask getCurrentTask();

   ITask getNextTask();

   boolean isKilled();

   void setKilled(boolean var1);

   Thread getThread();

   void setThread(Thread var1);

   ITask scheduleRunnableTask(Runnable var1, int var2);

   ITask scheduleDataInputStreamTask(URL var1);

   ITask schedule(int var1, int var2, int var3, Object var4);

   ITask scheduleSocketTask(String var1, int var2);

   ITask scheduleINetTask(int var1);

   void stop();
}
