package com.volgagas.personalassistant.models.network.orders;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by CaramelHeaven on 19:19, 27/02/2019.
 */
public class PurchOrderLinesNetwork {
    @SerializedName("@odata.etag")
    @Expose
    private String odataEtag;
    @SerializedName("dataAreaId")
    @Expose
    private String dataAreaId;
    @SerializedName("PurchaseOrderNumber")
    @Expose
    private String purchaseOrderNumber;
    @SerializedName("LineNumber")
    @Expose
    private Integer lineNumber;
    @SerializedName("Tax1099SClosingDate")
    @Expose
    private String tax1099SClosingDate;
    @SerializedName("IntrastatSpecialMovementCode")
    @Expose
    private String intrastatSpecialMovementCode;
    @SerializedName("AllowedUnderdeliveryPercentage")
    @Expose
    private Integer allowedUnderdeliveryPercentage;
    @SerializedName("WillProductReceivingCrossDockProducts")
    @Expose
    private String willProductReceivingCrossDockProducts;
    @SerializedName("IntrastatPortId")
    @Expose
    private String intrastatPortId;
    @SerializedName("Tax1099GVendorStateTaxId")
    @Expose
    private String tax1099GVendorStateTaxId;
    @SerializedName("OrderedInventoryStatusId")
    @Expose
    private String orderedInventoryStatusId;
    @SerializedName("ProductStyleId")
    @Expose
    private String productStyleId;
    @SerializedName("Tax1099Amount")
    @Expose
    private Integer tax1099Amount;
    @SerializedName("DeliveryAddressLocationId")
    @Expose
    private String deliveryAddressLocationId;
    @SerializedName("DeliveryAddressCountyId")
    @Expose
    private String deliveryAddressCountyId;
    @SerializedName("OriginStateId")
    @Expose
    private String originStateId;
    @SerializedName("IntrastatCommodityCode")
    @Expose
    private String intrastatCommodityCode;
    @SerializedName("DeliveryAddressCountryRegionISOCode")
    @Expose
    private String deliveryAddressCountryRegionISOCode;
    @SerializedName("ReceivingSiteId")
    @Expose
    private String receivingSiteId;
    @SerializedName("MultilineDiscountAmount")
    @Expose
    private Integer multilineDiscountAmount;
    @SerializedName("LineDiscountAmount")
    @Expose
    private Integer lineDiscountAmount;
    @SerializedName("WithholdingTaxGroupCode")
    @Expose
    private String withholdingTaxGroupCode;
    @SerializedName("IsPartialDeliveryPrevented")
    @Expose
    private String isPartialDeliveryPrevented;
    @SerializedName("MainAccountIdDisplayValue")
    @Expose
    private String mainAccountIdDisplayValue;
    @SerializedName("LineDescription")
    @Expose
    private String lineDescription;
    @SerializedName("ItemNumber")
    @Expose
    private String itemNumber;
    @SerializedName("Tax1099GVendorStateId")
    @Expose
    private String tax1099GVendorStateId;
    @SerializedName("IsProjectPayWhenPaid")
    @Expose
    private String isProjectPayWhenPaid;
    @SerializedName("DeliveryAddressZipCode")
    @Expose
    private String deliveryAddressZipCode;
    @SerializedName("DIOTOperationType")
    @Expose
    private String dIOTOperationType;
    @SerializedName("ConfirmedShippingDate")
    @Expose
    private String confirmedShippingDate;
    @SerializedName("RouteId")
    @Expose
    private String routeId;
    @SerializedName("IsNewFixedAsset")
    @Expose
    private String isNewFixedAsset;
    @SerializedName("Tax1099SAddressOrLegalDescription")
    @Expose
    private String tax1099SAddressOrLegalDescription;
    @SerializedName("Tax1099GTaxYear")
    @Expose
    private Integer tax1099GTaxYear;
    @SerializedName("GSTHSTTaxType")
    @Expose
    private String gSTHSTTaxType;
    @SerializedName("PurchaseOrderLineCreationMethod")
    @Expose
    private String purchaseOrderLineCreationMethod;
    @SerializedName("Tax1099StateId")
    @Expose
    private String tax1099StateId;
    @SerializedName("FormattedDelveryAddress")
    @Expose
    private String formattedDelveryAddress;
    @SerializedName("DeliveryAddressDistrictName")
    @Expose
    private String deliveryAddressDistrictName;
    @SerializedName("DeliveryCityInKana")
    @Expose
    private String deliveryCityInKana;
    @SerializedName("DeliveryAddressDunsNumber")
    @Expose
    private String deliveryAddressDunsNumber;
    @SerializedName("BOMId")
    @Expose
    private String bOMId;
    @SerializedName("LineAmount")
    @Expose
    private Integer lineAmount;
    @SerializedName("ServiceFiscalInformationCode")
    @Expose
    private String serviceFiscalInformationCode;
    @SerializedName("IsIntrastatTriangularDeal")
    @Expose
    private String isIntrastatTriangularDeal;
    @SerializedName("ProductSizeId")
    @Expose
    private String productSizeId;
    @SerializedName("LineDiscountPercentage")
    @Expose
    private Integer lineDiscountPercentage;
    @SerializedName("IntrastatTransactionCode")
    @Expose
    private String intrastatTransactionCode;
    @SerializedName("Tax1099GStateTaxWithheldAmount")
    @Expose
    private Integer tax1099GStateTaxWithheldAmount;
    @SerializedName("FixedPriceCharges")
    @Expose
    private Integer fixedPriceCharges;
    @SerializedName("BarCodeSetupId")
    @Expose
    private String barCodeSetupId;
    @SerializedName("PurchaseOrderLineStatus")
    @Expose
    private String purchaseOrderLineStatus;
    @SerializedName("ProjectSalesPrice")
    @Expose
    private Integer projectSalesPrice;
    @SerializedName("SalesTaxGroupCode")
    @Expose
    private String salesTaxGroupCode;
    @SerializedName("IsTax1099GTradeOrBusinessIncome")
    @Expose
    private String isTax1099GTradeOrBusinessIncome;
    @SerializedName("FixedAssetValueModelId")
    @Expose
    private String fixedAssetValueModelId;
    @SerializedName("OrderedCatchWeightQuantity")
    @Expose
    private Integer orderedCatchWeightQuantity;
    @SerializedName("IntrastatTransportModeCode")
    @Expose
    private String intrastatTransportModeCode;
    @SerializedName("NGPCode")
    @Expose
    private Integer nGPCode;
    @SerializedName("Tax1099SBuyerPartOfRealEstateTaxAmount")
    @Expose
    private Integer tax1099SBuyerPartOfRealEstateTaxAmount;
    @SerializedName("Tax1099StateAmount")
    @Expose
    private Integer tax1099StateAmount;
    @SerializedName("DeliveryAddressStreet")
    @Expose
    private String deliveryAddressStreet;
    @SerializedName("PurchasePriceQuantity")
    @Expose
    private Integer purchasePriceQuantity;
    @SerializedName("PurchasePrice")
    @Expose
    private Integer purchasePrice;
    @SerializedName("IsDeliveryAddressPrivate")
    @Expose
    private String isDeliveryAddressPrivate;
    @SerializedName("CustomerReference")
    @Expose
    private String customerReference;
    @SerializedName("DeliveryAddressCity")
    @Expose
    private String deliveryAddressCity;
    @SerializedName("MultilineDiscountPercentage")
    @Expose
    private Integer multilineDiscountPercentage;
    @SerializedName("FixedAssetNumber")
    @Expose
    private String fixedAssetNumber;
    @SerializedName("IsLineStopped")
    @Expose
    private String isLineStopped;
    @SerializedName("CFOPCode")
    @Expose
    private String cFOPCode;
    @SerializedName("ProjectSalesCurrencyCode")
    @Expose
    private String projectSalesCurrencyCode;
    @SerializedName("ProductConfigurationId")
    @Expose
    private String productConfigurationId;
    @SerializedName("DeliveryAddressCountryRegionId")
    @Expose
    private String deliveryAddressCountryRegionId;
    @SerializedName("DeliveryBuildingCompliment")
    @Expose
    private String deliveryBuildingCompliment;
    @SerializedName("BudgetReservationDocumentNumber")
    @Expose
    private String budgetReservationDocumentNumber;
    @SerializedName("FixedAssetTransactionType")
    @Expose
    private String fixedAssetTransactionType;
    @SerializedName("BudgetReservationLineNumber")
    @Expose
    private Integer budgetReservationLineNumber;
    @SerializedName("ProjectActivityNumber")
    @Expose
    private String projectActivityNumber;
    @SerializedName("PurchaseUnitSymbol")
    @Expose
    private String purchaseUnitSymbol;
    @SerializedName("IsTax1099SPropertyOrServices")
    @Expose
    private String isTax1099SPropertyOrServices;
    @SerializedName("OrderedPurchaseQuantity")
    @Expose
    private Integer orderedPurchaseQuantity;
    @SerializedName("DeliveryAddressPostBox")
    @Expose
    private String deliveryAddressPostBox;
    @SerializedName("ProjectWorkerPersonnelNumber")
    @Expose
    private String projectWorkerPersonnelNumber;
    @SerializedName("DeliveryAddressDescription")
    @Expose
    private String deliveryAddressDescription;
    @SerializedName("SkipCreateAutoCharges")
    @Expose
    private String skipCreateAutoCharges;
    @SerializedName("RequestedShippingDate")
    @Expose
    private String requestedShippingDate;
    @SerializedName("DeliveryStreetInKana")
    @Expose
    private String deliveryStreetInKana;
    @SerializedName("SalesTaxItemGroupCode")
    @Expose
    private String salesTaxItemGroupCode;
    @SerializedName("DeliveryAddressLatitude")
    @Expose
    private Integer deliveryAddressLatitude;
    @SerializedName("ProjectId")
    @Expose
    private String projectId;
    @SerializedName("CustomerRequisitionNumber")
    @Expose
    private String customerRequisitionNumber;
    @SerializedName("IntrastatStatisticValue")
    @Expose
    private Integer intrastatStatisticValue;
    @SerializedName("OriginCountryRegionId")
    @Expose
    private String originCountryRegionId;
    @SerializedName("AllowedOverdeliveryPercentage")
    @Expose
    private Integer allowedOverdeliveryPercentage;
    @SerializedName("Tax1099BoxId")
    @Expose
    private String tax1099BoxId;
    @SerializedName("DeliveryAddressTimeZone")
    @Expose
    private Object deliveryAddressTimeZone;
    @SerializedName("Tax1099Type")
    @Expose
    private Object tax1099Type;
    @SerializedName("UnitWeight")
    @Expose
    private Integer unitWeight;
    @SerializedName("ProjectSalesUnitSymbol")
    @Expose
    private String projectSalesUnitSymbol;
    @SerializedName("ExternalItemNumber")
    @Expose
    private String externalItemNumber;
    @SerializedName("ItemBatchNumber")
    @Expose
    private String itemBatchNumber;
    @SerializedName("PurchaseRebateVendorGroupId")
    @Expose
    private String purchaseRebateVendorGroupId;
    @SerializedName("ConfirmedDeliveryDate")
    @Expose
    private String confirmedDeliveryDate;
    @SerializedName("DeliveryAddressName")
    @Expose
    private String deliveryAddressName;
    @SerializedName("ProductColorId")
    @Expose
    private String productColorId;
    @SerializedName("IntrastatStatisticsProcedureCode")
    @Expose
    private String intrastatStatisticsProcedureCode;
    @SerializedName("ProjectCategoryId")
    @Expose
    private String projectCategoryId;
    @SerializedName("DeliveryAddressStateId")
    @Expose
    private String deliveryAddressStateId;
    @SerializedName("ProjectLinePropertyId")
    @Expose
    private String projectLinePropertyId;
    @SerializedName("Barcode")
    @Expose
    private String barcode;
    @SerializedName("ProjectTaxGroupCode")
    @Expose
    private String projectTaxGroupCode;
    @SerializedName("ItemWithholdingTaxGroupCode")
    @Expose
    private String itemWithholdingTaxGroupCode;
    @SerializedName("ProjectTaxItemGroupCode")
    @Expose
    private String projectTaxItemGroupCode;
    @SerializedName("ProcurementProductCategoryName")
    @Expose
    private String procurementProductCategoryName;
    @SerializedName("DeliveryAddressStreetNumber")
    @Expose
    private String deliveryAddressStreetNumber;
    @SerializedName("RetailProductVariantNumber")
    @Expose
    private String retailProductVariantNumber;
    @SerializedName("DeliveryAddressLongitude")
    @Expose
    private Integer deliveryAddressLongitude;
    @SerializedName("FixedAssetGroupId")
    @Expose
    private String fixedAssetGroupId;
    @SerializedName("RequesterPersonnelNumber")
    @Expose
    private String requesterPersonnelNumber;
    @SerializedName("IsDeliveryAddressOrderSpecific")
    @Expose
    private String isDeliveryAddressOrderSpecific;
    @SerializedName("DefaultLedgerDimensionDisplayValue")
    @Expose
    private String defaultLedgerDimensionDisplayValue;
    @SerializedName("AccountingDistributionTemplateName")
    @Expose
    private String accountingDistributionTemplateName;
    @SerializedName("CatchWeightUnitSymbol")
    @Expose
    private String catchWeightUnitSymbol;
    @SerializedName("ReceivingWarehouseId")
    @Expose
    private String receivingWarehouseId;
    @SerializedName("VendorInvoiceMatchingPolicy")
    @Expose
    private String vendorInvoiceMatchingPolicy;
    @SerializedName("RequestedDeliveryDate")
    @Expose
    private String requestedDeliveryDate;

    public String getOdataEtag() {
        return odataEtag;
    }

    public String getDataAreaId() {
        return dataAreaId;
    }

    public String getPurchaseOrderNumber() {
        return purchaseOrderNumber;
    }

    public Integer getLineNumber() {
        return lineNumber;
    }

    public String getTax1099SClosingDate() {
        return tax1099SClosingDate;
    }

    public String getIntrastatSpecialMovementCode() {
        return intrastatSpecialMovementCode;
    }

    public Integer getAllowedUnderdeliveryPercentage() {
        return allowedUnderdeliveryPercentage;
    }

    public String getWillProductReceivingCrossDockProducts() {
        return willProductReceivingCrossDockProducts;
    }

    public String getIntrastatPortId() {
        return intrastatPortId;
    }

    public String getTax1099GVendorStateTaxId() {
        return tax1099GVendorStateTaxId;
    }

    public String getOrderedInventoryStatusId() {
        return orderedInventoryStatusId;
    }

    public String getProductStyleId() {
        return productStyleId;
    }

    public Integer getTax1099Amount() {
        return tax1099Amount;
    }

    public String getDeliveryAddressLocationId() {
        return deliveryAddressLocationId;
    }

    public String getDeliveryAddressCountyId() {
        return deliveryAddressCountyId;
    }

    public String getOriginStateId() {
        return originStateId;
    }

    public String getIntrastatCommodityCode() {
        return intrastatCommodityCode;
    }

    public String getDeliveryAddressCountryRegionISOCode() {
        return deliveryAddressCountryRegionISOCode;
    }

    public String getReceivingSiteId() {
        return receivingSiteId;
    }

    public Integer getMultilineDiscountAmount() {
        return multilineDiscountAmount;
    }

    public Integer getLineDiscountAmount() {
        return lineDiscountAmount;
    }

    public String getWithholdingTaxGroupCode() {
        return withholdingTaxGroupCode;
    }

    public String getIsPartialDeliveryPrevented() {
        return isPartialDeliveryPrevented;
    }

    public String getMainAccountIdDisplayValue() {
        return mainAccountIdDisplayValue;
    }

    public String getLineDescription() {
        return lineDescription;
    }

    public String getItemNumber() {
        return itemNumber;
    }

    public String getTax1099GVendorStateId() {
        return tax1099GVendorStateId;
    }

    public String getIsProjectPayWhenPaid() {
        return isProjectPayWhenPaid;
    }

    public String getDeliveryAddressZipCode() {
        return deliveryAddressZipCode;
    }

    public String getdIOTOperationType() {
        return dIOTOperationType;
    }

    public String getConfirmedShippingDate() {
        return confirmedShippingDate;
    }

    public String getRouteId() {
        return routeId;
    }

    public String getIsNewFixedAsset() {
        return isNewFixedAsset;
    }

    public String getTax1099SAddressOrLegalDescription() {
        return tax1099SAddressOrLegalDescription;
    }

    public Integer getTax1099GTaxYear() {
        return tax1099GTaxYear;
    }

    public String getgSTHSTTaxType() {
        return gSTHSTTaxType;
    }

    public String getPurchaseOrderLineCreationMethod() {
        return purchaseOrderLineCreationMethod;
    }

    public String getTax1099StateId() {
        return tax1099StateId;
    }

    public String getFormattedDelveryAddress() {
        return formattedDelveryAddress;
    }

    public String getDeliveryAddressDistrictName() {
        return deliveryAddressDistrictName;
    }

    public String getDeliveryCityInKana() {
        return deliveryCityInKana;
    }

    public String getDeliveryAddressDunsNumber() {
        return deliveryAddressDunsNumber;
    }

    public String getbOMId() {
        return bOMId;
    }

    public Integer getLineAmount() {
        return lineAmount;
    }

    public String getServiceFiscalInformationCode() {
        return serviceFiscalInformationCode;
    }

    public String getIsIntrastatTriangularDeal() {
        return isIntrastatTriangularDeal;
    }

    public String getProductSizeId() {
        return productSizeId;
    }

    public Integer getLineDiscountPercentage() {
        return lineDiscountPercentage;
    }

    public String getIntrastatTransactionCode() {
        return intrastatTransactionCode;
    }

    public Integer getTax1099GStateTaxWithheldAmount() {
        return tax1099GStateTaxWithheldAmount;
    }

    public Integer getFixedPriceCharges() {
        return fixedPriceCharges;
    }

    public String getBarCodeSetupId() {
        return barCodeSetupId;
    }

    public String getPurchaseOrderLineStatus() {
        return purchaseOrderLineStatus;
    }

    public Integer getProjectSalesPrice() {
        return projectSalesPrice;
    }

    public String getSalesTaxGroupCode() {
        return salesTaxGroupCode;
    }

    public String getIsTax1099GTradeOrBusinessIncome() {
        return isTax1099GTradeOrBusinessIncome;
    }

    public String getFixedAssetValueModelId() {
        return fixedAssetValueModelId;
    }

    public Integer getOrderedCatchWeightQuantity() {
        return orderedCatchWeightQuantity;
    }

    public String getIntrastatTransportModeCode() {
        return intrastatTransportModeCode;
    }

    public Integer getnGPCode() {
        return nGPCode;
    }

    public Integer getTax1099SBuyerPartOfRealEstateTaxAmount() {
        return tax1099SBuyerPartOfRealEstateTaxAmount;
    }

    public Integer getTax1099StateAmount() {
        return tax1099StateAmount;
    }

    public String getDeliveryAddressStreet() {
        return deliveryAddressStreet;
    }

    public Integer getPurchasePriceQuantity() {
        return purchasePriceQuantity;
    }

    public Integer getPurchasePrice() {
        return purchasePrice;
    }

    public String getIsDeliveryAddressPrivate() {
        return isDeliveryAddressPrivate;
    }

    public String getCustomerReference() {
        return customerReference;
    }

    public String getDeliveryAddressCity() {
        return deliveryAddressCity;
    }

    public Integer getMultilineDiscountPercentage() {
        return multilineDiscountPercentage;
    }

    public String getFixedAssetNumber() {
        return fixedAssetNumber;
    }

    public String getIsLineStopped() {
        return isLineStopped;
    }

    public String getcFOPCode() {
        return cFOPCode;
    }

    public String getProjectSalesCurrencyCode() {
        return projectSalesCurrencyCode;
    }

    public String getProductConfigurationId() {
        return productConfigurationId;
    }

    public String getDeliveryAddressCountryRegionId() {
        return deliveryAddressCountryRegionId;
    }

    public String getDeliveryBuildingCompliment() {
        return deliveryBuildingCompliment;
    }

    public String getBudgetReservationDocumentNumber() {
        return budgetReservationDocumentNumber;
    }

    public String getFixedAssetTransactionType() {
        return fixedAssetTransactionType;
    }

    public Integer getBudgetReservationLineNumber() {
        return budgetReservationLineNumber;
    }

    public String getProjectActivityNumber() {
        return projectActivityNumber;
    }

    public String getPurchaseUnitSymbol() {
        return purchaseUnitSymbol;
    }

    public String getIsTax1099SPropertyOrServices() {
        return isTax1099SPropertyOrServices;
    }

    public Integer getOrderedPurchaseQuantity() {
        return orderedPurchaseQuantity;
    }

    public String getDeliveryAddressPostBox() {
        return deliveryAddressPostBox;
    }

    public String getProjectWorkerPersonnelNumber() {
        return projectWorkerPersonnelNumber;
    }

    public String getDeliveryAddressDescription() {
        return deliveryAddressDescription;
    }

    public String getSkipCreateAutoCharges() {
        return skipCreateAutoCharges;
    }

    public String getRequestedShippingDate() {
        return requestedShippingDate;
    }

    public String getDeliveryStreetInKana() {
        return deliveryStreetInKana;
    }

    public String getSalesTaxItemGroupCode() {
        return salesTaxItemGroupCode;
    }

    public Integer getDeliveryAddressLatitude() {
        return deliveryAddressLatitude;
    }

    public String getProjectId() {
        return projectId;
    }

    public String getCustomerRequisitionNumber() {
        return customerRequisitionNumber;
    }

    public Integer getIntrastatStatisticValue() {
        return intrastatStatisticValue;
    }

    public String getOriginCountryRegionId() {
        return originCountryRegionId;
    }

    public Integer getAllowedOverdeliveryPercentage() {
        return allowedOverdeliveryPercentage;
    }

    public String getTax1099BoxId() {
        return tax1099BoxId;
    }

    public Object getDeliveryAddressTimeZone() {
        return deliveryAddressTimeZone;
    }

    public Object getTax1099Type() {
        return tax1099Type;
    }

    public Integer getUnitWeight() {
        return unitWeight;
    }

    public String getProjectSalesUnitSymbol() {
        return projectSalesUnitSymbol;
    }

    public String getExternalItemNumber() {
        return externalItemNumber;
    }

    public String getItemBatchNumber() {
        return itemBatchNumber;
    }

    public String getPurchaseRebateVendorGroupId() {
        return purchaseRebateVendorGroupId;
    }

    public String getConfirmedDeliveryDate() {
        return confirmedDeliveryDate;
    }

    public String getDeliveryAddressName() {
        return deliveryAddressName;
    }

    public String getProductColorId() {
        return productColorId;
    }

    public String getIntrastatStatisticsProcedureCode() {
        return intrastatStatisticsProcedureCode;
    }

    public String getProjectCategoryId() {
        return projectCategoryId;
    }

    public String getDeliveryAddressStateId() {
        return deliveryAddressStateId;
    }

    public String getProjectLinePropertyId() {
        return projectLinePropertyId;
    }

    public String getBarcode() {
        return barcode;
    }

    public String getProjectTaxGroupCode() {
        return projectTaxGroupCode;
    }

    public String getItemWithholdingTaxGroupCode() {
        return itemWithholdingTaxGroupCode;
    }

    public String getProjectTaxItemGroupCode() {
        return projectTaxItemGroupCode;
    }

    public String getProcurementProductCategoryName() {
        return procurementProductCategoryName;
    }

    public String getDeliveryAddressStreetNumber() {
        return deliveryAddressStreetNumber;
    }

    public String getRetailProductVariantNumber() {
        return retailProductVariantNumber;
    }

    public Integer getDeliveryAddressLongitude() {
        return deliveryAddressLongitude;
    }

    public String getFixedAssetGroupId() {
        return fixedAssetGroupId;
    }

    public String getRequesterPersonnelNumber() {
        return requesterPersonnelNumber;
    }

    public String getIsDeliveryAddressOrderSpecific() {
        return isDeliveryAddressOrderSpecific;
    }

    public String getDefaultLedgerDimensionDisplayValue() {
        return defaultLedgerDimensionDisplayValue;
    }

    public String getAccountingDistributionTemplateName() {
        return accountingDistributionTemplateName;
    }

    public String getCatchWeightUnitSymbol() {
        return catchWeightUnitSymbol;
    }

    public String getReceivingWarehouseId() {
        return receivingWarehouseId;
    }

    public String getVendorInvoiceMatchingPolicy() {
        return vendorInvoiceMatchingPolicy;
    }

    public String getRequestedDeliveryDate() {
        return requestedDeliveryDate;
    }
}
