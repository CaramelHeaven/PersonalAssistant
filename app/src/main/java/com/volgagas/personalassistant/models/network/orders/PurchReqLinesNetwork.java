package com.volgagas.personalassistant.models.network.orders;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by CaramelHeaven on 19:19, 27/02/2019.
 */
public class PurchReqLinesNetwork {
    @SerializedName("@odata.etag")
    @Expose
    private String odataEtag;
    @SerializedName("RequisitionNumber")
    @Expose
    private String requisitionNumber;
    @SerializedName("RequisitionLineNumber")
    @Expose
    private Integer requisitionLineNumber;
    @SerializedName("ProcurementProductCategoryName")
    @Expose
    private String procurementProductCategoryName;
    @SerializedName("ProjectSalesUnitSymbol")
    @Expose
    private String projectSalesUnitSymbol;
    @SerializedName("LineStatus")
    @Expose
    private String lineStatus;
    @SerializedName("DeliveryAddressBuildingCompliment")
    @Expose
    private String deliveryAddressBuildingCompliment;
    @SerializedName("ProjectCategoryId")
    @Expose
    private String projectCategoryId;
    @SerializedName("AccountingDistributionTemplateName")
    @Expose
    private String accountingDistributionTemplateName;
    @SerializedName("ItemNumber")
    @Expose
    private String itemNumber;
    @SerializedName("DeliveryAddressDescription")
    @Expose
    private String deliveryAddressDescription;
    @SerializedName("BuyingLegalEntityId")
    @Expose
    private String buyingLegalEntityId;
    @SerializedName("DeliveryCityInKana")
    @Expose
    private String deliveryCityInKana;
    @SerializedName("DeliveryStreetInKana")
    @Expose
    private String deliveryStreetInKana;
    @SerializedName("LineDiscountAmount")
    @Expose
    private Integer lineDiscountAmount;
    @SerializedName("ProductStyleId")
    @Expose
    private String productStyleId;
    @SerializedName("ProjectTaxItemGroupCode")
    @Expose
    private String projectTaxItemGroupCode;
    @SerializedName("ProjectTaxGroupCode")
    @Expose
    private String projectTaxGroupCode;
    @SerializedName("AccountingDate")
    @Expose
    private String accountingDate;
    @SerializedName("ProductConfigurationId")
    @Expose
    private String productConfigurationId;
    @SerializedName("IsPartialDeliveryPrevented")
    @Expose
    private String isPartialDeliveryPrevented;
    @SerializedName("DeliveryAddressCountryRegionId")
    @Expose
    private String deliveryAddressCountryRegionId;
    @SerializedName("ItemBatchNumber")
    @Expose
    private String itemBatchNumber;
    @SerializedName("DeliveryAddressLatitude")
    @Expose
    private Integer deliveryAddressLatitude;
    @SerializedName("ReceivingWarehouseId")
    @Expose
    private String receivingWarehouseId;
    @SerializedName("DeliveryAddressCity")
    @Expose
    private String deliveryAddressCity;
    @SerializedName("PurchaseUnitSymbol")
    @Expose
    private String purchaseUnitSymbol;
    @SerializedName("VendorAccountNumber")
    @Expose
    private String vendorAccountNumber;
    @SerializedName("ProjectId")
    @Expose
    private String projectId;
    @SerializedName("BusinessJustificationDetails")
    @Expose
    private String businessJustificationDetails;
    @SerializedName("ProjectLinePropertyId")
    @Expose
    private String projectLinePropertyId;
    @SerializedName("DeliveryAddressDistrictName")
    @Expose
    private String deliveryAddressDistrictName;
    @SerializedName("DeliveryAddressCountyId")
    @Expose
    private String deliveryAddressCountyId;
    @SerializedName("ProductSizeId")
    @Expose
    private String productSizeId;
    @SerializedName("DeliveryAddressZipCode")
    @Expose
    private String deliveryAddressZipCode;
    @SerializedName("FixedPriceCharges")
    @Expose
    private Integer fixedPriceCharges;
    @SerializedName("DeliveryAddressDunsNumber")
    @Expose
    private String deliveryAddressDunsNumber;
    @SerializedName("PurchasePriceQuantity")
    @Expose
    private Integer purchasePriceQuantity;
    @SerializedName("RequisitionerPersonnelNumber")
    @Expose
    private String requisitionerPersonnelNumber;
    @SerializedName("DeliveryAddressName")
    @Expose
    private String deliveryAddressName;
    @SerializedName("BudgetReservationLineNumber")
    @Expose
    private Integer budgetReservationLineNumber;
    @SerializedName("DeliveryAddressStreetNumber")
    @Expose
    private String deliveryAddressStreetNumber;
    @SerializedName("LineType")
    @Expose
    private String lineType;
    @SerializedName("IsDeliveryAddressPrivate")
    @Expose
    private String isDeliveryAddressPrivate;
    @SerializedName("FixedAssetRuleQualifierOptionName")
    @Expose
    private String fixedAssetRuleQualifierOptionName;
    @SerializedName("DeliveryAttentionInformation")
    @Expose
    private String deliveryAttentionInformation;
    @SerializedName("DeliveryAddressCountryRegionISOCode")
    @Expose
    private String deliveryAddressCountryRegionISOCode;
    @SerializedName("ReceivingSiteId")
    @Expose
    private String receivingSiteId;
    @SerializedName("ProjectSalesCurrencyCode")
    @Expose
    private String projectSalesCurrencyCode;
    @SerializedName("ReceivingOperatingUnitNumber")
    @Expose
    private String receivingOperatingUnitNumber;
    @SerializedName("URL")
    @Expose
    private String uRL;
    @SerializedName("CurrencyCode")
    @Expose
    private String currencyCode;
    @SerializedName("DeliveryAddressLocationId")
    @Expose
    private String deliveryAddressLocationId;
    @SerializedName("ProjectActivityNumber")
    @Expose
    private String projectActivityNumber;
    @SerializedName("SalesTaxItemGroupCode")
    @Expose
    private String salesTaxItemGroupCode;
    @SerializedName("ProductName")
    @Expose
    private String productName;
    @SerializedName("IsPrepaymentRequired")
    @Expose
    private String isPrepaymentRequired;
    @SerializedName("PrePaymentDetails")
    @Expose
    private String prePaymentDetails;
    @SerializedName("LineDescription")
    @Expose
    private String lineDescription;
    @SerializedName("RFQRequirementLevel")
    @Expose
    private String rFQRequirementLevel;
    @SerializedName("DeliveryAddressStreet")
    @Expose
    private String deliveryAddressStreet;
    @SerializedName("SalesTaxGroupCode")
    @Expose
    private String salesTaxGroupCode;
    @SerializedName("IsDeliveryAddressOrderSpecific")
    @Expose
    private String isDeliveryAddressOrderSpecific;
    @SerializedName("PurchasePrice")
    @Expose
    private Integer purchasePrice;
    @SerializedName("LineDiscountPercentage")
    @Expose
    private Integer lineDiscountPercentage;
    @SerializedName("RequestedDate")
    @Expose
    private String requestedDate;
    @SerializedName("BusinessJustificationCode")
    @Expose
    private String businessJustificationCode;
    @SerializedName("DeliveryAddressLongitude")
    @Expose
    private Integer deliveryAddressLongitude;
    @SerializedName("FixedAssetGroupId")
    @Expose
    private String fixedAssetGroupId;
    @SerializedName("DefaultLedgerDimensionDisplayValue")
    @Expose
    private String defaultLedgerDimensionDisplayValue;
    @SerializedName("DeliveryAddressTimeZone")
    @Expose
    private Object deliveryAddressTimeZone;
    @SerializedName("BudgetReservationDocumentNumber")
    @Expose
    private String budgetReservationDocumentNumber;
    @SerializedName("ProductColorId")
    @Expose
    private String productColorId;
    @SerializedName("DeliveryAddressStateId")
    @Expose
    private String deliveryAddressStateId;
    @SerializedName("DeliveryAddressPostBox")
    @Expose
    private String deliveryAddressPostBox;
    @SerializedName("LineAmount")
    @Expose
    private Integer lineAmount;
    @SerializedName("FixedAssetReasonCode")
    @Expose
    private String fixedAssetReasonCode;
    @SerializedName("ProjectSalesPrice")
    @Expose
    private Integer projectSalesPrice;
    @SerializedName("RequestedPurchaseQuantity")
    @Expose
    private Integer requestedPurchaseQuantity;
    @SerializedName("ExternalItemNumber")
    @Expose
    private String externalItemNumber;
    @SerializedName("FormattedDeliveryAddress")
    @Expose
    private String formattedDeliveryAddress;

    public String getOdataEtag() {
        return odataEtag;
    }

    public String getRequisitionNumber() {
        return requisitionNumber;
    }

    public Integer getRequisitionLineNumber() {
        return requisitionLineNumber;
    }

    public String getProcurementProductCategoryName() {
        return procurementProductCategoryName;
    }

    public String getProjectSalesUnitSymbol() {
        return projectSalesUnitSymbol;
    }

    public String getLineStatus() {
        return lineStatus;
    }

    public String getDeliveryAddressBuildingCompliment() {
        return deliveryAddressBuildingCompliment;
    }

    public String getProjectCategoryId() {
        return projectCategoryId;
    }

    public String getAccountingDistributionTemplateName() {
        return accountingDistributionTemplateName;
    }

    public String getItemNumber() {
        return itemNumber;
    }

    public String getDeliveryAddressDescription() {
        return deliveryAddressDescription;
    }

    public String getBuyingLegalEntityId() {
        return buyingLegalEntityId;
    }

    public String getDeliveryCityInKana() {
        return deliveryCityInKana;
    }

    public String getDeliveryStreetInKana() {
        return deliveryStreetInKana;
    }

    public Integer getLineDiscountAmount() {
        return lineDiscountAmount;
    }

    public String getProductStyleId() {
        return productStyleId;
    }

    public String getProjectTaxItemGroupCode() {
        return projectTaxItemGroupCode;
    }

    public String getProjectTaxGroupCode() {
        return projectTaxGroupCode;
    }

    public String getAccountingDate() {
        return accountingDate;
    }

    public String getProductConfigurationId() {
        return productConfigurationId;
    }

    public String getIsPartialDeliveryPrevented() {
        return isPartialDeliveryPrevented;
    }

    public String getDeliveryAddressCountryRegionId() {
        return deliveryAddressCountryRegionId;
    }

    public String getItemBatchNumber() {
        return itemBatchNumber;
    }

    public Integer getDeliveryAddressLatitude() {
        return deliveryAddressLatitude;
    }

    public String getReceivingWarehouseId() {
        return receivingWarehouseId;
    }

    public String getDeliveryAddressCity() {
        return deliveryAddressCity;
    }

    public String getPurchaseUnitSymbol() {
        return purchaseUnitSymbol;
    }

    public String getVendorAccountNumber() {
        return vendorAccountNumber;
    }

    public String getProjectId() {
        return projectId;
    }

    public String getBusinessJustificationDetails() {
        return businessJustificationDetails;
    }

    public String getProjectLinePropertyId() {
        return projectLinePropertyId;
    }

    public String getDeliveryAddressDistrictName() {
        return deliveryAddressDistrictName;
    }

    public String getDeliveryAddressCountyId() {
        return deliveryAddressCountyId;
    }

    public String getProductSizeId() {
        return productSizeId;
    }

    public String getDeliveryAddressZipCode() {
        return deliveryAddressZipCode;
    }

    public Integer getFixedPriceCharges() {
        return fixedPriceCharges;
    }

    public String getDeliveryAddressDunsNumber() {
        return deliveryAddressDunsNumber;
    }

    public Integer getPurchasePriceQuantity() {
        return purchasePriceQuantity;
    }

    public String getRequisitionerPersonnelNumber() {
        return requisitionerPersonnelNumber;
    }

    public String getDeliveryAddressName() {
        return deliveryAddressName;
    }

    public Integer getBudgetReservationLineNumber() {
        return budgetReservationLineNumber;
    }

    public String getDeliveryAddressStreetNumber() {
        return deliveryAddressStreetNumber;
    }

    public String getLineType() {
        return lineType;
    }

    public String getIsDeliveryAddressPrivate() {
        return isDeliveryAddressPrivate;
    }

    public String getFixedAssetRuleQualifierOptionName() {
        return fixedAssetRuleQualifierOptionName;
    }

    public String getDeliveryAttentionInformation() {
        return deliveryAttentionInformation;
    }

    public String getDeliveryAddressCountryRegionISOCode() {
        return deliveryAddressCountryRegionISOCode;
    }

    public String getReceivingSiteId() {
        return receivingSiteId;
    }

    public String getProjectSalesCurrencyCode() {
        return projectSalesCurrencyCode;
    }

    public String getReceivingOperatingUnitNumber() {
        return receivingOperatingUnitNumber;
    }

    public String getuRL() {
        return uRL;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public String getDeliveryAddressLocationId() {
        return deliveryAddressLocationId;
    }

    public String getProjectActivityNumber() {
        return projectActivityNumber;
    }

    public String getSalesTaxItemGroupCode() {
        return salesTaxItemGroupCode;
    }

    public String getProductName() {
        return productName;
    }

    public String getIsPrepaymentRequired() {
        return isPrepaymentRequired;
    }

    public String getPrePaymentDetails() {
        return prePaymentDetails;
    }

    public String getLineDescription() {
        return lineDescription;
    }

    public String getrFQRequirementLevel() {
        return rFQRequirementLevel;
    }

    public String getDeliveryAddressStreet() {
        return deliveryAddressStreet;
    }

    public String getSalesTaxGroupCode() {
        return salesTaxGroupCode;
    }

    public String getIsDeliveryAddressOrderSpecific() {
        return isDeliveryAddressOrderSpecific;
    }

    public Integer getPurchasePrice() {
        return purchasePrice;
    }

    public Integer getLineDiscountPercentage() {
        return lineDiscountPercentage;
    }

    public String getRequestedDate() {
        return requestedDate;
    }

    public String getBusinessJustificationCode() {
        return businessJustificationCode;
    }

    public Integer getDeliveryAddressLongitude() {
        return deliveryAddressLongitude;
    }

    public String getFixedAssetGroupId() {
        return fixedAssetGroupId;
    }

    public String getDefaultLedgerDimensionDisplayValue() {
        return defaultLedgerDimensionDisplayValue;
    }

    public Object getDeliveryAddressTimeZone() {
        return deliveryAddressTimeZone;
    }

    public String getBudgetReservationDocumentNumber() {
        return budgetReservationDocumentNumber;
    }

    public String getProductColorId() {
        return productColorId;
    }

    public String getDeliveryAddressStateId() {
        return deliveryAddressStateId;
    }

    public String getDeliveryAddressPostBox() {
        return deliveryAddressPostBox;
    }

    public Integer getLineAmount() {
        return lineAmount;
    }

    public String getFixedAssetReasonCode() {
        return fixedAssetReasonCode;
    }

    public Integer getProjectSalesPrice() {
        return projectSalesPrice;
    }

    public Integer getRequestedPurchaseQuantity() {
        return requestedPurchaseQuantity;
    }

    public String getExternalItemNumber() {
        return externalItemNumber;
    }

    public String getFormattedDeliveryAddress() {
        return formattedDeliveryAddress;
    }
}
