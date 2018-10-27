package com.zinios.dealab.services.util;

import com.google.api.client.util.Strings;
import com.zinios.dealab.models.Branch;
import com.zinios.dealab.models.Company;
import com.zinios.dealab.models.Deal;

public class ValidatorUtil {

	public static boolean validateCompany(Company company) {
		return !Strings.isNullOrEmpty(company.getName()) && company.getCategory() != null;
	}

	public static boolean validateBranch(Branch branch) {
		return !Strings.isNullOrEmpty(branch.getName())
				&& !Strings.isNullOrEmpty(branch.getDescription())
				&& !Strings.isNullOrEmpty(branch.getAddress())
				&& !Strings.isNullOrEmpty(branch.getContact())
				&& branch.getCompany() != null
				&& branch.getCompany().getId() != null
				&& branch.getLat() != null && branch.getLat().doubleValue() != 0
				&& branch.getLng() != null && branch.getLng().doubleValue() != 0;
	}

	public static boolean validateDeal(Deal deal) {
		return !Strings.isNullOrEmpty(deal.getDescription())
				&& !Strings.isNullOrEmpty(deal.getNote())
				&& deal.getStartDate() != null && !Strings.isNullOrEmpty(deal.getStartDate().toString())
				&& deal.getEndDate() != null && !Strings.isNullOrEmpty(deal.getEndDate().toString());
	}
//
//	public static boolean validateMedia(Media media) {
//		return media.getType() != null
//				&& !Strings.isNullOrEmpty(media.getName())
//				&& !Strings.isNullOrEmpty(media.getUrl())
//				&& media.getCompany() != null
//				&& media.getCompany().getId() != null;
//	}
//
//	public static boolean validateProductMedia(ProductMedia productMedia) {
//		return productMedia.getMedia() != null && productMedia.getMedia().getId() != 0L
//				&& productMedia.getProduct() != null && productMedia.getProduct().getId() != 0;
//	}
//
//	public static boolean validateDisplay(Display display) {
//		return !Strings.isNullOrEmpty(display.getMac())
//				&& display.getMac().length() < 45
//				&& display.getBranch() != null
//				&& display.getBranch().getId() != null;
//	}
//
//	public static boolean validateDisplayMedia(DisplayMedia displayMedia) {
//		return displayMedia.getMedia() != null && displayMedia.getMedia().getId() != 0L
//				&& displayMedia.getDisplay() != null && displayMedia.getDisplay().getId() != 0;
//	}
//
//	public static boolean validateMdm(Mdm mdm) {
//		return !Strings.isNullOrEmpty(mdm.getMac())
//				&& mdm.getMac().length() < 45
//				&& mdm.getType() != null;
//	}
//
//	public static boolean validateProductLocation(ProductLocation productLocation) {
//		return productLocation.getProduct() != null
//				&& productLocation.getMdm() != null;
//	}
}