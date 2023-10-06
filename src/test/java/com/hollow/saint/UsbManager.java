package com.hollow.saint;

import java.util.EnumSet;
import java.util.Set;

public class UsbManager {
	private final EnumSet<UsbFlags> mUsbState = EnumSet.noneOf(UsbFlags.class);

	public enum UsbFlags {CONNECTED, NCM, ACCESSORY, ADB, MTP}

	public void addFlag(UsbFlags flag) {
		mUsbState.add(flag);
		System.out.println("After add flag " + flag + ", Now state is " + this.mUsbState);
	}

	public void addFlag(Set flags) {
		mUsbState.addAll(flags);
		System.out.println("After add flags " + flags + ", Now state is " + this.mUsbState);
	}

	public void removeFlag(UsbFlags flag) {
		mUsbState.remove(flag);
		System.out.println("After remove flag " + flag + ", Now state is " + this.mUsbState);
	}

	public void removeFlag(Set flags) {
		mUsbState.removeAll(flags);
		System.out.println("After remove flags " + flags + ", Now state is " + this.mUsbState);
	}

	public boolean checkFlagEnabled(UsbFlags flag) {
		return mUsbState.contains(flag);
	}

	public boolean checkFlagEnabled(Set flag) {
		return mUsbState.containsAll(flag);
	}

	public void printUsbState() {
		System.out.println("Current usb state is " + mUsbState);
	}

	public static void main(String[] args) {
		UsbManager usbManager = new UsbManager();
		usbManager.printUsbState();
		// 添加一项 flag
		usbManager.addFlag(UsbFlags.CONNECTED);
		// 添加一组 flag
		usbManager.addFlag(EnumSet.of(UsbFlags.ACCESSORY, UsbFlags.ADB));
		// 检查存在的一个flag
		System.out.println("mUsbState contains flag " + UsbFlags.ACCESSORY + ": " + usbManager.checkFlagEnabled(UsbFlags.ACCESSORY));
		// 检查不存在的一个flag
		System.out.println("mUsbState contains flag " + UsbFlags.MTP + ": " + usbManager.checkFlagEnabled(UsbFlags.MTP));
		// 检查一组存在的flag
		System.out.println("mUsbState contains flag " + EnumSet.of(UsbFlags.ACCESSORY, UsbFlags.ADB) + ": " + usbManager.checkFlagEnabled(EnumSet.of(UsbFlags.ACCESSORY, UsbFlags.ADB)));
		// 检查一组包含不存在的flag
		System.out.println("mUsbState contains flag " + EnumSet.of(UsbFlags.ACCESSORY, UsbFlags.MTP) + ": " + usbManager.checkFlagEnabled(EnumSet.of(UsbFlags.ACCESSORY, UsbFlags.MTP)));
		// 检查一组都不存在的flag
		System.out.println("mUsbState contains flag " + EnumSet.of(UsbFlags.NCM, UsbFlags.MTP) + ": " + usbManager.checkFlagEnabled(EnumSet.of(UsbFlags.NCM, UsbFlags.MTP)));
		usbManager.printUsbState();
		// 删除一个不存在的flag
		usbManager.removeFlag(UsbFlags.MTP);
		// 删除一个存在的flag
		usbManager.removeFlag(UsbFlags.ACCESSORY);
		// 删除一组都不存在的flag
		usbManager.removeFlag(EnumSet.of(UsbFlags.NCM, UsbFlags.MTP));
		// 删除一组包含不存在的flag
		usbManager.removeFlag(EnumSet.of(UsbFlags.NCM, UsbFlags.ADB));
		usbManager.addFlag(EnumSet.of(UsbFlags.ACCESSORY, UsbFlags.ADB));
		// 删除一组存在的flag
		usbManager.removeFlag(EnumSet.of(UsbFlags.ADB, UsbFlags.ACCESSORY));
	}
}
