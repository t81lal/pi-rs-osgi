/************************************************************************************
 * pi rs - a generic framework for loading Java Applets in a contained environment. *
 * Copyright (C) 2015  NullBool                                                     *
 *                                                                                  *
 * This program is free software: you can redistribute it and/or modify             *
 * it under the terms of the GNU General Public License as published by             *
 * the Free Software Foundation, either version 3 of the License, or                *
 * (at your option) any later version.                                              *
 *                                                                                  *
 * This program is distributed in the hope that it will be useful,                  *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of                   *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the                    *
 * GNU General Public License for more details.                                     *
 *                                                                                  *
 * You should have received a copy of the GNU General Public License                *
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.            *
 ************************************************************************************/

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
