<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="imui" uri="http://www.intra-mart.co.jp/taglib/imui"%>
<%@ taglib prefix="imart" uri="http://www.intra-mart.co.jp/taglib/core/standard"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="f" uri="http://terasoluna.org/functions"%>

<!-- Multiple Data Choice -->
<header class="imui-chapter-title">
	<h2>Multiple Data Selection</h2>
</header>

<table class="imui-form tab_header">
	<colgroup>
		<col style="width: 180px;" />
		<col style="width: 70px;" />
		<col style="width: auto;" />
	</colgroup>
	<tbody>
		<!-- Multi Data Selection -->
		<tr>
			<th colspan="${thColspan}">
				<label class="imui-required">Multiple Data Selection</label>
			</th>
			<td>
				<label class="pointer-locked" style="display: block; margin-bottom: 4px;">
					<input type="checkbox" tabindex="-1" ${f:h(savedFormData.f_multidata_pl)} /> PL Impact
				</label>
				<label class="pointer-locked" style="display: block; margin-bottom: 4px;">
					<input type="checkbox" tabindex="-1" ${f:h(savedFormData.f_multidata_asset)} /> Asset
				</label>
				<label class="pointer-locked" style="display: block;">
					<input type="checkbox" tabindex="-1" ${f:h(savedFormData.f_multidata_estimated)} /> Estimated Schedule
				</label>
			</td>
		</tr>
	</tbody>
</table>

<!-- PL Impact -->
<c:if test="${savedFormData.f_multidata_pl == 'checked'}">
	<div id="section-pl">
	<header class="imui-chapter-title">
		<h2>PL Impact</h2>
	</header>

	<table class="imui-form tab_header">
		<colgroup>
			<col style="width: 30%;" />
			<col style="width: 20%;" />
			<col style="width: 30%;" />
			<col style="width: 20%;" />
		</colgroup>
		<tbody>
			<!-- Header Row -->
			<tr>
				<th style="text-align: left;">
					<label class="imui-required">Budget PL Impact to current FY</label>
				</th>
				<th style="text-align: left;">
					<label class="imui-required">Month</label>
				</th>
				<th style="text-align: left;">
					<label class="imui-required">PL Impact to current FY</label>
				</th>
				<th style="text-align: left;">
					<label class="imui-required">Month</label>
				</th>
			</tr>

			<!-- Input Row underneath -->
			<tr>
				<td>
					<label class="font-imart">${f:h(savedFormData.f_budget_pl_impact)}</label>
				</td>
				<td>
					<label class="font-imart">${f:h(savedFormData.f_budget_pl_month)}</label>
				</td>
				<td>
					<label class="font-imart">${f:h(savedFormData.f_pl_impact)}</label>
				</td>
				<td>
					<label class="font-imart">${f:h(savedFormData.f_pl_month)}</label>
				</td>
			</tr>
		</tbody>
	</table>
</div>
</c:if>

<!-- Asset -->
<c:if test="${savedFormData.f_multidata_asset == 'checked'}">
	<div id="section-asset">
	<header class="imui-chapter-title">
		<h2>Asset</h2>
	</header>

	<table class="imui-form tab_header">
		<colgroup>
			<col style="width: 50%;" />
			<col style="width: 50%;" />
		</colgroup>
		<tbody>
			<!-- Header Row -->
			<tr>
				<th style="text-align: left;">
					<label class="imui-required">Asset Number</label>
				</th>
				<th style="text-align: left;">
					<label class="imui-required">Book Value</label>
				</th>
			</tr>

			<!-- Input Row underneath -->
			<tr>
				<td>
					<label class="font-imart">${f:h(savedFormData.f_asset_number)}</label>
				</td>
				<td>
					<label class="font-imart">${f:h(savedFormData.f_book_value)}</label>
				</td>
			</tr>
		</tbody>
	</table>
</div>
</c:if>

<!-- Estimated Schedule (Payment Conditions) -->
<c:if test="${savedFormData.f_multidata_estimated == 'checked'}">
<div id="section-estimated">
	<header class="imui-chapter-title">
		<h2>Estimated Schedule (Payment Conditions)</h2>
	</header>

	<div style="overflow-x: auto; width: 100%; margin-bottom: 15px;">
		<table id="payment_detail_table" class="imui-form tab_header" style="min-width: 950px; table-layout: fixed;">
			<colgroup>
				<col style="width: 40px;" />
				<!-- No. -->
				<col style="width: 220px;" />
				<!-- Brand and Type -->
				<col style="width: 140px;" />
				<!-- Amount -->
				<col style="width: 130px;" />
				<!-- Date -->
				<col style="width: 140px;" />
				<!-- Category -->
				<col style="width: 120px;" />
				<!-- Recurring -->
				<col style="width: 150px;" />
				<!-- Paid By -->
			</colgroup>
			<thead>
				<tr>
					<th colspan="7" style="text-align: left;">
						<label class="imui-required">Payment (Total Cash Flow Impact)</label>
					</th>
				</tr>
				<tr>
					<th style="text-align: center;">
						<label>No</label>
					</th>
					<th style="text-align: center;">
						<label class="imui-required">Brand and Type</label>
					</th>
					<th style="text-align: center;">
						<label class="imui-required">Amount</label>
					</th>
					<th style="text-align: center;">
						<label class="imui-required">Date</label>
					</th>
					<th style="text-align: center;">
						<label class="imui-required">Category</label>
					</th>
					<th style="text-align: center;">
						<label class="imui-required">Recurring</label>
					</th>
					<th style="text-align: center;">
						<label class="imui-required">Paid By</label>
					</th>
				</tr>
			</thead>
			<tbody>
				<!-- Dynamic Rows from Database -->
				<c:forEach items="${savedFormData.d_list_payment_detail}" var="item">
					<tr class="payment-row">
						<td style="text-align: center;" class="row-seq-no">
							<label class="font-imart">${f:h(item.row_no)}</label>
						</td>
						<td style="text-align: center;">
							<label class="font-imart">
								${f:h(item.brand)}
								<c:if test="${not empty item.brand and not empty item.type}">&nbsp;/&nbsp;</c:if>
								${f:h(item.type)}
							</label>
						</td>
						<td style="text-align: center;">
							<label class="font-imart payment-amount">${f:h(item.payment_amount)}</label>
						</td>
						<td style="text-align: center;">
							<label class="font-imart">${f:h(item.payment_date)}</label>
						</td>
						<td style="text-align: center;">
							<label class="font-imart">
								<c:choose>
									<c:when test="${item.category == '1'}">Equipment</c:when>
									<c:when test="${item.category == '2'}">Software</c:when>
									<c:when test="${item.category == '3'}">Utility</c:when>
									<c:when test="${item.category == '4'}">Service</c:when>
									<c:when test="${item.category == '5'}">Other</c:when>
									<c:otherwise>-</c:otherwise>
								</c:choose>
							</label>
						</td>
						<td>
							<table style="margin: 0px;">
								<tr>
									<td style="border: none; padding: 0px;">
										<input class="pointer-locked" tabindex="-1" type="radio" ${f:h(item.recurring_yes)}>
									</td>
									<td style="border: none; padding: 0px;">
										<label>Yes</label>
									</td>
								</tr>
								<tr>
									<td style="border: none; padding: 0px;">
										<input class="pointer-locked" tabindex="-1" type="radio" ${f:h(item.recurring_no)}>
									</td>
									<td style="border: none; padding: 0px;">
										<label>No</label>
									</td>
								</tr>
							</table>
						</td>
						<td>
							<label class="pointer-locked" style="display: block; margin-bottom: 2px;">
								<input type="checkbox" tabindex="-1" ${f:h(item.paid_by_card)} /> Card
							</label>
							<label class="pointer-locked" style="display: block;">
								<input type="checkbox" tabindex="-1" ${f:h(item.paid_by_cash)} /> Cash
							</label>
						</td>
					</tr>
				</c:forEach>
			</tbody>
			<tfoot>
				<tr>
					<th colspan="7" style="text-align: left;">
						<label class="imui-required">Total Amount</label>
					</th>
				</tr>
				<tr>
					<td colspan="7">
						<label class="font-imart" style="font-weight: bold !important;" id="f_total_payment_amount">
							${f:h(savedFormData.f_total_payment_amount)}
						</label>
					</td>
				</tr>
			</tfoot>
		</table>
	</div>
</div>
</c:if>


