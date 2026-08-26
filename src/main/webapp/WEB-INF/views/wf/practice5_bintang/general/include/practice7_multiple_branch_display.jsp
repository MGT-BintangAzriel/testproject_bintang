<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="imui" uri="http://www.intra-mart.co.jp/taglib/imui"%>
<%@ taglib prefix="imart" uri="http://www.intra-mart.co.jp/taglib/core/standard"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="f" uri="http://terasoluna.org/functions"%>

<!-- Agreement Classification -->
<header class="imui-chapter-title">
	<h2>Agreement Classification</h2>
</header>

<table class="imui-form tab_header">
	<colgroup>
		<col style="width: 180px;" />
		<col style="width: 70px;" />
		<col style="width: auto;" />
	</colgroup>
	<tbody>
		<!-- Agreement Classification & PD Approval Sub-Conditions -->
		<tr>
			<th colspan="${thColspan}">
				<label class="imui-required">Agreement Classification</label>
			</th>
			<td>
				<div class="mt-4">
					<label class="pointer-locked"> <input disabled tabindex="-1" type="radio" ${f:h(savedFormData.f_agreement_classification_pd)}> PD
						Approval (either one of condition below)
					</label>
					<div class="sub-options">
						<div>
							<label class="radio-item-sub-gap pointer-locked"> <input disabled tabindex="-1" type="radio"
								${f:h(savedFormData.f_agreement_classification_pd_more_than_1_billion)}> Agreement with amount is equal or more than 1 billion
							</label>
						</div>

						<div>
							<label class="pointer-locked"> <input disabled tabindex="-1" type="radio"
								${f:h(savedFormData.f_agreement_classification_pd_more_than_12_months)}> Period is equal or more than 12 months
							</label>
						</div>

						<div>
							<label class="pointer-locked"> <input disabled tabindex="-1" type="radio"
								${f:h(savedFormData.f_agreement_classification_pd_specific_party)}> Agreement related to specific party
							</label>
							<div class="sub-option-note">
								<em>Bank, Related Parties, Dealer, Consultant/Lawyer/Appraise (Vendor head-hunter, ISO Certification, HR System development, etc),
									Government, Production (Component and Parts), Customer, Etc</em>
							</div>
						</div>

						<div>
							<label class="pointer-locked"> <input disabled tabindex="-1" type="radio"
								${f:h(savedFormData.f_agreement_classification_pd_special_issue)}> Special issue
							</label>
							<div class="sub-option-note">
								<em>New project/issue (more than 50 M), not included in budget plan</em>
							</div>
						</div>

						<div>
							<label class="pointer-locked"> <input disabled tabindex="-1" type="radio"
								${f:h(savedFormData.f_agreement_classification_pd_direct_procurement)}> Direct Procurement due to either of the 2 cases below:
							</label>
							<div class="sub-option-note">
								<em>1. Emergency procurement</em>
							</div>
							<div class="sub-option-note">
								<em>2. Specific Goods / Items (refer to PSD Guideline)</em>
							</div>
						</div>

						<div>
							<label class="pointer-locked"> <input disabled tabindex="-1" type="radio"
								${f:h(savedFormData.f_agreement_classification_pd_agreement_not_more_than_12_months)}> Agreement not more than 12 months
							</label>
						</div>

					</div>
				</div>
				<div class="mt-4">
					<label class="pointer-locked"> <input disabled tabindex="-1" type="radio"
						${f:h(savedFormData.f_agreement_classification_dic_director_approval)}> DIC Director Approval
					</label>
				</div>
			</td>
		</tr>
		<!-- EC Approval & Sub-Conditions -->
		<tr>
			<th colspan="${thColspan}">
				<label class="imui-required">EC Approval is Required or Not</label>
			</th>
			<td>
				<div class="mt-4">
					<label class="pointer-locked"> <input disabled tabindex="-1" type="radio"
						${f:h(savedFormData.f_agreement_classification_ec_approval_yes)}> Yes
					</label>
					<div class="sub-options">
						<div>
							<label class="radio-item-sub-gap pointer-locked"> <input disabled tabindex="-1" type="radio"
								${f:h(savedFormData.f_agreement_classification_ec_amount_equal_more_than_1_billion)}> Amount is equal or more than 1 billion
							</label>
						</div>

						<div>
							<label class="pointer-locked"> <input disabled tabindex="-1" type="radio"
								${f:h(savedFormData.f_agreement_classification_ec_period_equal_more_than_12_months)}> Period is equal or more than 12 months
							</label>
						</div>

						<div>
							<label class="pointer-locked"> <input disabled tabindex="-1" type="radio"
								${f:h(savedFormData.f_agreement_classification_ec_escalate_issue_to_ec)}> Director believes it is necessary to escalate the issue to
								EC
							</label>
						</div>
					</div>
				</div>
				<div class="mt-4">
					<label class="pointer-locked"> <input disabled tabindex="-1" type="radio" ${f:h(savedFormData.f_agreement_classification_ec_approval_no)}>
						No
					</label>
				</div>
			</td>
		</tr>
	</tbody>
</table>