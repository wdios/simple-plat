package com.wis.tookit.jcaptcha;

import com.octo.captcha.service.captchastore.FastHashMapCaptchaStore;
import com.octo.captcha.service.image.DefaultManageableImageCaptchaService;
import com.octo.captcha.service.image.ImageCaptchaService;

/**
 * Jcaptcha 单例模式
 */
public class CaptchaServiceSingleton {

	// 不允许构造实例
	private CaptchaServiceSingleton() { }

	private static ImageCaptchaService instance = null;

	/**
	 * SimpleListSoundCaptchaEngine //还可以用声音 SpellerSoundCaptchaEngine
	 * SpellerSoundCaptchaEngine DefaultGimpyEngineCaptcha
	 * BaffleListGimpyEngineCaptcha BasicListGimpyEngineCaptcha
	 * DeformedBaffleListGimpyEngineCaptcha DoubleRandomListGimpyEngineCaptcha
	 * SimpleListImageCaptchaEngineCaptcha SimpleFishEyeEngineCaptcha
	 */
	// 传入样式类
	static {
		instance = new DefaultManageableImageCaptchaService(new FastHashMapCaptchaStore(), new ImageCaptchaEngineExtend(), 180, 100000, 75000);
	}

	public static ImageCaptchaService getInstance() {
		return instance;
	}

}
