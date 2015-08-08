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

package org.nullbool.piexternal.game.api;


public final class Bank {
	
//   private static final int BANK = 12;
//   private static final int ITEMS = 10;
//   private static final int[] QUANTITIES = new int[]{0, 1, 5, 10};
//   private static final String[] BANKER = new String[]{"Banker", "Banker tutor"};
//   private static final String[] ACTIONS_INVENT = new String[]{"Deposit-All", "Deposit-1", "Deposit-5", "Deposit-10", "Deposit-X"};
//   private static final String[] ACTIONS_BANK = new String[]{"Withdraw-All", "Withdraw-1", "Withdraw-5", "Withdraw-10", "Withdraw-X"};
//
//   public Stream stream() {
//      return Arrays.stream(this.getItems());
//   }
//
//   public boolean isOpen() {
//      return this.api.widgets.getWidget(12) != null;
//   }
//
//   public boolean deposit(int id, int quantity) {
//      return this.deposit(this.api.inventory.getItem(id), quantity);
//   }
//
//   public boolean deposit(String name, int quantity) {
//      return this.deposit(this.api.inventory.getItem(name), quantity);
//   }
//
//   public boolean withdraw(int id, int quantity) {
//      Utilities.sleep((long)(this.checkIfLoaded()?0:Utilities.next(3500, 4000)));
//      return this.checkIfLoaded() && this.withdraw(this.getItem(id), quantity);
//   }
//
//   public boolean withdraw(String name, int quantity) {
//      Utilities.sleep((long)(this.checkIfLoaded()?0:Utilities.next(3500, 4000)));
//      return this.checkIfLoaded() && this.withdraw(this.getItem(name), quantity);
//   }
//
//   public RSItem getItem(String n) {
//      Stream s = this.stream().filter((c) -> {
//         return c.bankName().equals(n);
//      });
//      return (RSItem)s.findFirst().orElse((Object)null);
//   }
//
//   public RSItem getItem(int id) {
//      return (RSItem)this.stream().filter((c) -> {
//         return c.getId() == id;
//      }).findFirst().orElse((Object)null);
//   }
//
//   public boolean close() {
//      int child = Child.CLOSE_BUTTON.getChild();
//      this.api.widgets.getWidgetChild(12, 1).getChild(child).interact();
//      Utilities.sleep((long)Utilities.next(1000, 1700));
//      return !this.isOpen();
//   }
//
//   public boolean openUsingBooth() {
//      ((RSObject)this.api.objects.pool().sort().name(new String[]{"Bank booth"}).first()).interact();
//      Utilities.sleep(1500, 2000);
//      return this.isOpen();
//   }
//
//   public boolean openUsingNpc() {
//      RSNpc banker = (RSNpc)this.api.npcs.pool().sort().name(BANKER).first();
//      boolean validation = banker != null && banker.isOnScreen();
//      boolean condition = validation?banker.interact("Bank"):false;
//      Timer time = new Timer(2000L);
//
//      while(time.isRunning() && !this.isOpen() && condition) {
//         Utilities.sleep(50L);
//      }
//
//      return this.isOpen();
//   }
//
//   public boolean deposit(RSItem item, int q) {
//      String action = this.getActions(q, ACTIONS_INVENT);
//      boolean validation = item != null && this.isOpen();
//      boolean condition = validation?item.interact(action):false;
//      boolean write = condition && action.equals("Deposit-X");
//      this.writeQuantity(write, q);
//      Utilities.sleep((long)(write?0:Utilities.next(700, 1200)));
//      return this.api.inventory.getRealCount(item.getId()) < q;
//   }
//
//   public boolean withdraw(RSItem i, int q) {
//      String act = this.getActions(q, ACTIONS_BANK);
//      boolean validate = i != null && this.isOpen();
//      int count = this.api.inventory.getRealCount(i.bankName());
//      boolean condition = validate?i.getChild().interact(act):false;
//      boolean write = condition && act.equals("Withdraw-X");
//      this.writeQuantity(write, q);
//      Utilities.sleep((long)(write?0:Utilities.next(700, 1200)));
//      return this.api.inventory.getRealCount(i.bankName()) > count;
//   }
//
//   private boolean checkIfLoaded() {
//      RSWidgetChild child = this.api.widgets.getWidgetChild(12, 10);
//      Stream s = Arrays.stream(child.getAllChildren());
//      s = s.filter((c) -> {
//         return c.getName() != null && !c.getName().equals("");
//      });
//      return this.isOpen() && s.count() != 0L;
//   }
//
//   public void depositEquipment() {
//      int child = Child.DEPOSIT_INV.getChild();
//      this.api.widgets.getWidgetChild(12, child).interact();
//      Utilities.sleep((long)Utilities.next(1000, 1700));
//   }
//
//   public void depositInventory() {
//      int child = Child.DEPOSIT_INV.getChild();
//      this.api.widgets.getWidgetChild(12, child).interact();
//      Timer time = new Timer(2000L);
//
//      while(time.isRunning() && this.api.inventory.getCount() != 0) {
//         Utilities.sleep(50L);
//      }
//
//   }
//
//   private String getActions(int quantity, String... actions) {
//      int index = Arrays.binarySearch(QUANTITIES, quantity);
//      int cleanIndex = index >= 0?index:actions.length - 1;
//      return actions[cleanIndex];
//   }
//
//   private void writeQuantity(boolean actionX, int quantity) {
//      if(actionX) {
//         Utilities.sleep(2000, 2500);
//         this.api.keyboard.writeText(String.valueOf(quantity));
//         this.api.keyboard.sendMousePressed('\n');
//      }
//
//   }
//
//   public RSItem[] getItems() {
//      ArrayList items = new ArrayList();
//      RSWidgetChild child = this.api.widgets.getWidgetChild(12, 10);
//      Stream stream = Arrays.stream(child.getAllChildren());
//      stream.forEach((childItem) -> {
//         items.add(new RSItem(this.api, childItem));
//      });
//      return (RSItem[])items.toArray(new RSItem[items.size()]);
//   }
}
