package com.volgagas.personalassistant.models.network.orders;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by CaramelHeaven on 19:07, 27/02/2019.
 */
public class PurchaseOrderNetwork {
    @SerializedName("@odata.etag")
    @Expose
    private String odataEtag;
    @SerializedName("dataAreaId")
    @Expose
    private String dataAreaId;
    @SerializedName("PurchaseOrderNumber")
    @Expose
    private String purchaseOrderNumber;
    @SerializedName("VendorInvoiceDeclarationId")
    @Expose
    private String vendorInvoiceDeclarationId;
    @SerializedName("ExpectedStoreAvailableSalesDate")
    @Expose
    private String expectedStoreAvailableSalesDate;
    @SerializedName("DeliveryModeId")
    @Expose
    private String deliveryModeId;
    @SerializedName("InvoiceAddressStreet")
    @Expose
    private String invoiceAddressStreet;
    @SerializedName("OrderVendorAccountNumber")
    @Expose
    private String orderVendorAccountNumber;
    @SerializedName("Email")
    @Expose
    private String email;
    @SerializedName("TransportationModeId")
    @Expose
    private String transportationModeId;
    @SerializedName("IsChangeManagementActive")
    @Expose
    private String isChangeManagementActive;
    @SerializedName("AccountingDistributionTemplateName")
    @Expose
    private String accountingDistributionTemplateName;
    @SerializedName("DeliveryAddressDescription")
    @Expose
    private String deliveryAddressDescription;
    @SerializedName("VendorTransactionSettlementType")
    @Expose
    private String vendorTransactionSettlementType;
    @SerializedName("DeliveryCityInKana")
    @Expose
    private String deliveryCityInKana;
    @SerializedName("DeliveryStreetInKana")
    @Expose
    private String deliveryStreetInKana;
    @SerializedName("ReasonComment")
    @Expose
    private String reasonComment;
    @SerializedName("NumberSequenceGroupId")
    @Expose
    private String numberSequenceGroupId;
    @SerializedName("TransportationTemplateId")
    @Expose
    private String transportationTemplateId;
    @SerializedName("AccountingDate")
    @Expose
    private String accountingDate;
    @SerializedName("CashDiscountPercentage")
    @Expose
    private Integer cashDiscountPercentage;
    @SerializedName("PurchaseOrderName")
    @Expose
    private String purchaseOrderName;
    @SerializedName("RequestedDeliveryDate")
    @Expose
    private String requestedDeliveryDate;
    @SerializedName("DeliveryAddressCountryRegionId")
    @Expose
    private String deliveryAddressCountryRegionId;
    @SerializedName("DeliveryAddressLatitude")
    @Expose
    private Integer deliveryAddressLatitude;
    @SerializedName("MultilineDiscountVendorGroupCode")
    @Expose
    private String multilineDiscountVendorGroupCode;
    @SerializedName("DeliveryAddressCity")
    @Expose
    private String deliveryAddressCity;
    @SerializedName("ConfirmedDeliveryDate")
    @Expose
    private String confirmedDeliveryDate;
    @SerializedName("PurchaseRebateVendorGroupId")
    @Expose
    private String purchaseRebateVendorGroupId;
    @SerializedName("InvoiceAddressCounty")
    @Expose
    private String invoiceAddressCounty;
    @SerializedName("ChargeVendorGroupId")
    @Expose
    private String chargeVendorGroupId;
    @SerializedName("RequesterPersonnelNumber")
    @Expose
    private String requesterPersonnelNumber;
    @SerializedName("ProjectId")
    @Expose
    private String projectId;
    @SerializedName("ShippingCarrierId")
    @Expose
    private String shippingCarrierId;
    @SerializedName("TotalDiscountPercentage")
    @Expose
    private Integer totalDiscountPercentage;
    @SerializedName("PriceVendorGroupCode")
    @Expose
    private String priceVendorGroupCode;
    @SerializedName("PurchaseOrderHeaderCreationMethod")
    @Expose
    private String purchaseOrderHeaderCreationMethod;
    @SerializedName("DeliveryAddressDistrictName")
    @Expose
    private String deliveryAddressDistrictName;
    @SerializedName("DeliveryAddressCountyId")
    @Expose
    private String deliveryAddressCountyId;
    @SerializedName("DeliveryAddressZipCode")
    @Expose
    private String deliveryAddressZipCode;
    @SerializedName("IsConsolidatedInvoiceTarget")
    @Expose
    private String isConsolidatedInvoiceTarget;
    @SerializedName("ConfirmingPurchaseOrderCode")
    @Expose
    private String confirmingPurchaseOrderCode;
    @SerializedName("LanguageId")
    @Expose
    private String languageId;
    @SerializedName("ReasonCode")
    @Expose
    private String reasonCode;
    @SerializedName("DeliveryAddressDunsNumber")
    @Expose
    private String deliveryAddressDunsNumber;
    @SerializedName("DeliveryTermsId")
    @Expose
    private String deliveryTermsId;
    @SerializedName("BankDocumentType")
    @Expose
    private String bankDocumentType;
    @SerializedName("ExpectedStoreReceiptDate")
    @Expose
    private String expectedStoreReceiptDate;
    @SerializedName("DeliveryAddressName")
    @Expose
    private String deliveryAddressName;
    @SerializedName("InvoiceAddressCountryRegionId")
    @Expose
    private String invoiceAddressCountryRegionId;
    @SerializedName("ReplenishmentServiceCategoryId")
    @Expose
    private String replenishmentServiceCategoryId;
    @SerializedName("PurchaseOrderPoolId")
    @Expose
    private String purchaseOrderPoolId;
    @SerializedName("DeliveryAddressStreetNumber")
    @Expose
    private String deliveryAddressStreetNumber;
    @SerializedName("ExpectedCrossDockingDate")
    @Expose
    private String expectedCrossDockingDate;
    @SerializedName("InvoiceAddressStreetNumber")
    @Expose
    private String invoiceAddressStreetNumber;
    @SerializedName("TaxExemptNumber")
    @Expose
    private String taxExemptNumber;
    @SerializedName("IsDeliveryAddressPrivate")
    @Expose
    private String isDeliveryAddressPrivate;
    @SerializedName("FormattedInvoiceAddress")
    @Expose
    private String formattedInvoiceAddress;
    @SerializedName("BuyerGroupId")
    @Expose
    private String buyerGroupId;
    @SerializedName("DeliveryAddressCountryRegionISOCode")
    @Expose
    private String deliveryAddressCountryRegionISOCode;
    @SerializedName("CashDiscountCode")
    @Expose
    private String cashDiscountCode;
    @SerializedName("PaymentScheduleName")
    @Expose
    private String paymentScheduleName;
    @SerializedName("IntrastatTransactionCode")
    @Expose
    private String intrastatTransactionCode;
    @SerializedName("URL")
    @Expose
    private String uRL;
    @SerializedName("ConfirmingPurchaseOrderCodeLanguageId")
    @Expose
    private String confirmingPurchaseOrderCodeLanguageId;
    @SerializedName("CurrencyCode")
    @Expose
    private String currencyCode;
    @SerializedName("InvoiceType")
    @Expose
    private String invoiceType;
    @SerializedName("ArePricesIncludingSalesTax")
    @Expose
    private String arePricesIncludingSalesTax;
    @SerializedName("DeliveryAddressLocationId")
    @Expose
    private String deliveryAddressLocationId;
    @SerializedName("GSTSelfBilledInvoiceApprovalNumber")
    @Expose
    private String gSTSelfBilledInvoiceApprovalNumber;
    @SerializedName("IsDeliveredDirectly")
    @Expose
    private String isDeliveredDirectly;
    @SerializedName("IntrastatStatisticsProcedureCode")
    @Expose
    private String intrastatStatisticsProcedureCode;
    @SerializedName("InvoiceVendorAccountNumber")
    @Expose
    private String invoiceVendorAccountNumber;
    @SerializedName("DeliveryAddressStreet")
    @Expose
    private String deliveryAddressStreet;
    @SerializedName("VendorOrderReference")
    @Expose
    private String vendorOrderReference;
    @SerializedName("ReplenishmentWarehouseId")
    @Expose
    private String replenishmentWarehouseId;
    @SerializedName("FixedDueDate")
    @Expose
    private String fixedDueDate;
    @SerializedName("TransportationDocumentLineId")
    @Expose
    private String transportationDocumentLineId;
    @SerializedName("SalesTaxGroupCode")
    @Expose
    private String salesTaxGroupCode;
    @SerializedName("IsDeliveryAddressOrderSpecific")
    @Expose
    private String isDeliveryAddressOrderSpecific;
    @SerializedName("VendorPostingProfileId")
    @Expose
    private String vendorPostingProfileId;
    @SerializedName("VendorPaymentMethodSpecificationName")
    @Expose
    private String vendorPaymentMethodSpecificationName;
    @SerializedName("InvoiceAddressCity")
    @Expose
    private String invoiceAddressCity;
    @SerializedName("ShippingCarrierServiceGroupId")
    @Expose
    private String shippingCarrierServiceGroupId;
    @SerializedName("ContactPersonId")
    @Expose
    private String contactPersonId;
    @SerializedName("DefaultReceivingWarehouseId")
    @Expose
    private String defaultReceivingWarehouseId;
    @SerializedName("EUSalesListCode")
    @Expose
    private String eUSalesListCode;
    @SerializedName("ImportDeclarationNumber")
    @Expose
    private String importDeclarationNumber;
    @SerializedName("PurchaseOrderStatus")
    @Expose
    private String purchaseOrderStatus;
    @SerializedName("PaymentTermsName")
    @Expose
    private String paymentTermsName;
    @SerializedName("DeliveryAddressLongitude")
    @Expose
    private Integer deliveryAddressLongitude;
    @SerializedName("DocumentApprovalStatus")
    @Expose
    private String documentApprovalStatus;
    @SerializedName("InvoiceAddressZipCode")
    @Expose
    private String invoiceAddressZipCode;
    @SerializedName("ShippingCarrierServiceId")
    @Expose
    private String shippingCarrierServiceId;
    @SerializedName("DefaultLedgerDimensionDisplayValue")
    @Expose
    private String defaultLedgerDimensionDisplayValue;
    @SerializedName("DeliveryAddressTimeZone")
    @Expose
    private Object deliveryAddressTimeZone;
    @SerializedName("AttentionInformation")
    @Expose
    private String attentionInformation;
    @SerializedName("DeliveryAddressStateId")
    @Expose
    private String deliveryAddressStateId;
    @SerializedName("DeliveryBuildingCompliment")
    @Expose
    private String deliveryBuildingCompliment;
    @SerializedName("IntrastatTransportModeCode")
    @Expose
    private String intrastatTransportModeCode;
    @SerializedName("DeliveryAddressPostBox")
    @Expose
    private String deliveryAddressPostBox;
    @SerializedName("IsOneTimeVendor")
    @Expose
    private String isOneTimeVendor;
    @SerializedName("IntrastatPortId")
    @Expose
    private String intrastatPortId;
    @SerializedName("OrdererPersonnelNumber")
    @Expose
    private String ordererPersonnelNumber;
    @SerializedName("VendorPaymentMethodName")
    @Expose
    private String vendorPaymentMethodName;
    @SerializedName("InvoiceAddressState")
    @Expose
    private String invoiceAddressState;
    @SerializedName("DefaultReceivingSiteId")
    @Expose
    private String defaultReceivingSiteId;
    @SerializedName("LineDiscountVendorGroupCode")
    @Expose
    private String lineDiscountVendorGroupCode;
    @SerializedName("TransportationRoutePlanId")
    @Expose
    private String transportationRoutePlanId;
    @SerializedName("ZakatContractNumber")
    @Expose
    private String zakatContractNumber;
    @SerializedName("FormattedDeliveryAddress")
    @Expose
    private String formattedDeliveryAddress;
    @SerializedName("TotalDiscountVendorGroupCode")
    @Expose
    private String totalDiscountVendorGroupCode;

    @Override
    public String toString() {
        return "PurchaseOrderNetwork{" +
                "odataEtag='" + odataEtag + '\'' +
                ", dataAreaId='" + dataAreaId + '\'' +
                ", purchaseOrderNumber='" + purchaseOrderNumber + '\'' +
                ", vendorInvoiceDeclarationId='" + vendorInvoiceDeclarationId + '\'' +
                ", expectedStoreAvailableSalesDate='" + expectedStoreAvailableSalesDate + '\'' +
                ", deliveryModeId='" + deliveryModeId + '\'' +
                ", invoiceAddressStreet='" + invoiceAddressStreet + '\'' +
                ", orderVendorAccountNumber='" + orderVendorAccountNumber + '\'' +
                ", email='" + email + '\'' +
                ", transportationModeId='" + transportationModeId + '\'' +
                ", isChangeManagementActive='" + isChangeManagementActive + '\'' +
                ", accountingDistributionTemplateName='" + accountingDistributionTemplateName + '\'' +
                ", deliveryAddressDescription='" + deliveryAddressDescription + '\'' +
                ", vendorTransactionSettlementType='" + vendorTransactionSettlementType + '\'' +
                ", deliveryCityInKana='" + deliveryCityInKana + '\'' +
                ", deliveryStreetInKana='" + deliveryStreetInKana + '\'' +
                ", reasonComment='" + reasonComment + '\'' +
                ", numberSequenceGroupId='" + numberSequenceGroupId + '\'' +
                ", transportationTemplateId='" + transportationTemplateId + '\'' +
                ", accountingDate='" + accountingDate + '\'' +
                ", cashDiscountPercentage=" + cashDiscountPercentage +
                ", purchaseOrderName='" + purchaseOrderName + '\'' +
                ", requestedDeliveryDate='" + requestedDeliveryDate + '\'' +
                ", deliveryAddressCountryRegionId='" + deliveryAddressCountryRegionId + '\'' +
                ", deliveryAddressLatitude=" + deliveryAddressLatitude +
                ", multilineDiscountVendorGroupCode='" + multilineDiscountVendorGroupCode + '\'' +
                ", deliveryAddressCity='" + deliveryAddressCity + '\'' +
                ", confirmedDeliveryDate='" + confirmedDeliveryDate + '\'' +
                ", purchaseRebateVendorGroupId='" + purchaseRebateVendorGroupId + '\'' +
                ", invoiceAddressCounty='" + invoiceAddressCounty + '\'' +
                ", chargeVendorGroupId='" + chargeVendorGroupId + '\'' +
                ", requesterPersonnelNumber='" + requesterPersonnelNumber + '\'' +
                ", projectId='" + projectId + '\'' +
                ", shippingCarrierId='" + shippingCarrierId + '\'' +
                ", totalDiscountPercentage=" + totalDiscountPercentage +
                ", priceVendorGroupCode='" + priceVendorGroupCode + '\'' +
                ", purchaseOrderHeaderCreationMethod='" + purchaseOrderHeaderCreationMethod + '\'' +
                ", deliveryAddressDistrictName='" + deliveryAddressDistrictName + '\'' +
                ", deliveryAddressCountyId='" + deliveryAddressCountyId + '\'' +
                ", deliveryAddressZipCode='" + deliveryAddressZipCode + '\'' +
                ", isConsolidatedInvoiceTarget='" + isConsolidatedInvoiceTarget + '\'' +
                ", confirmingPurchaseOrderCode='" + confirmingPurchaseOrderCode + '\'' +
                ", languageId='" + languageId + '\'' +
                ", reasonCode='" + reasonCode + '\'' +
                ", deliveryAddressDunsNumber='" + deliveryAddressDunsNumber + '\'' +
                ", deliveryTermsId='" + deliveryTermsId + '\'' +
                ", bankDocumentType='" + bankDocumentType + '\'' +
                ", expectedStoreReceiptDate='" + expectedStoreReceiptDate + '\'' +
                ", deliveryAddressName='" + deliveryAddressName + '\'' +
                ", invoiceAddressCountryRegionId='" + invoiceAddressCountryRegionId + '\'' +
                ", replenishmentServiceCategoryId='" + replenishmentServiceCategoryId + '\'' +
                ", purchaseOrderPoolId='" + purchaseOrderPoolId + '\'' +
                ", deliveryAddressStreetNumber='" + deliveryAddressStreetNumber + '\'' +
                ", expectedCrossDockingDate='" + expectedCrossDockingDate + '\'' +
                ", invoiceAddressStreetNumber='" + invoiceAddressStreetNumber + '\'' +
                ", taxExemptNumber='" + taxExemptNumber + '\'' +
                ", isDeliveryAddressPrivate='" + isDeliveryAddressPrivate + '\'' +
                ", formattedInvoiceAddress='" + formattedInvoiceAddress + '\'' +
                ", buyerGroupId='" + buyerGroupId + '\'' +
                ", deliveryAddressCountryRegionISOCode='" + deliveryAddressCountryRegionISOCode + '\'' +
                ", cashDiscountCode='" + cashDiscountCode + '\'' +
                ", paymentScheduleName='" + paymentScheduleName + '\'' +
                ", intrastatTransactionCode='" + intrastatTransactionCode + '\'' +
                ", uRL='" + uRL + '\'' +
                ", confirmingPurchaseOrderCodeLanguageId='" + confirmingPurchaseOrderCodeLanguageId + '\'' +
                ", currencyCode='" + currencyCode + '\'' +
                ", invoiceType='" + invoiceType + '\'' +
                ", arePricesIncludingSalesTax='" + arePricesIncludingSalesTax + '\'' +
                ", deliveryAddressLocationId='" + deliveryAddressLocationId + '\'' +
                ", gSTSelfBilledInvoiceApprovalNumber='" + gSTSelfBilledInvoiceApprovalNumber + '\'' +
                ", isDeliveredDirectly='" + isDeliveredDirectly + '\'' +
                ", intrastatStatisticsProcedureCode='" + intrastatStatisticsProcedureCode + '\'' +
                ", invoiceVendorAccountNumber='" + invoiceVendorAccountNumber + '\'' +
                ", deliveryAddressStreet='" + deliveryAddressStreet + '\'' +
                ", vendorOrderReference='" + vendorOrderReference + '\'' +
                ", replenishmentWarehouseId='" + replenishmentWarehouseId + '\'' +
                ", fixedDueDate='" + fixedDueDate + '\'' +
                ", transportationDocumentLineId='" + transportationDocumentLineId + '\'' +
                ", salesTaxGroupCode='" + salesTaxGroupCode + '\'' +
                ", isDeliveryAddressOrderSpecific='" + isDeliveryAddressOrderSpecific + '\'' +
                ", vendorPostingProfileId='" + vendorPostingProfileId + '\'' +
                ", vendorPaymentMethodSpecificationName='" + vendorPaymentMethodSpecificationName + '\'' +
                ", invoiceAddressCity='" + invoiceAddressCity + '\'' +
                ", shippingCarrierServiceGroupId='" + shippingCarrierServiceGroupId + '\'' +
                ", contactPersonId='" + contactPersonId + '\'' +
                ", defaultReceivingWarehouseId='" + defaultReceivingWarehouseId + '\'' +
                ", eUSalesListCode='" + eUSalesListCode + '\'' +
                ", importDeclarationNumber='" + importDeclarationNumber + '\'' +
                ", purchaseOrderStatus='" + purchaseOrderStatus + '\'' +
                ", paymentTermsName='" + paymentTermsName + '\'' +
                ", deliveryAddressLongitude=" + deliveryAddressLongitude +
                ", documentApprovalStatus='" + documentApprovalStatus + '\'' +
                ", invoiceAddressZipCode='" + invoiceAddressZipCode + '\'' +
                ", shippingCarrierServiceId='" + shippingCarrierServiceId + '\'' +
                ", defaultLedgerDimensionDisplayValue='" + defaultLedgerDimensionDisplayValue + '\'' +
                ", deliveryAddressTimeZone=" + deliveryAddressTimeZone +
                ", attentionInformation='" + attentionInformation + '\'' +
                ", deliveryAddressStateId='" + deliveryAddressStateId + '\'' +
                ", deliveryBuildingCompliment='" + deliveryBuildingCompliment + '\'' +
                ", intrastatTransportModeCode='" + intrastatTransportModeCode + '\'' +
                ", deliveryAddressPostBox='" + deliveryAddressPostBox + '\'' +
                ", isOneTimeVendor='" + isOneTimeVendor + '\'' +
                ", intrastatPortId='" + intrastatPortId + '\'' +
                ", ordererPersonnelNumber='" + ordererPersonnelNumber + '\'' +
                ", vendorPaymentMethodName='" + vendorPaymentMethodName + '\'' +
                ", invoiceAddressState='" + invoiceAddressState + '\'' +
                ", defaultReceivingSiteId='" + defaultReceivingSiteId + '\'' +
                ", lineDiscountVendorGroupCode='" + lineDiscountVendorGroupCode + '\'' +
                ", transportationRoutePlanId='" + transportationRoutePlanId + '\'' +
                ", zakatContractNumber='" + zakatContractNumber + '\'' +
                ", formattedDeliveryAddress='" + formattedDeliveryAddress + '\'' +
                ", totalDiscountVendorGroupCode='" + totalDiscountVendorGroupCode + '\'' +
                '}';
    }

    public String getOdataEtag() {
        return odataEtag;
    }

    public String getDataAreaId() {
        return dataAreaId;
    }

    public String getPurchaseOrderNumber() {
        return purchaseOrderNumber;
    }

    public String getVendorInvoiceDeclarationId() {
        return vendorInvoiceDeclarationId;
    }

    public String getExpectedStoreAvailableSalesDate() {
        return expectedStoreAvailableSalesDate;
    }

    public String getDeliveryModeId() {
        return deliveryModeId;
    }

    public String getInvoiceAddressStreet() {
        return invoiceAddressStreet;
    }

    public String getOrderVendorAccountNumber() {
        return orderVendorAccountNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getTransportationModeId() {
        return transportationModeId;
    }

    public String getIsChangeManagementActive() {
        return isChangeManagementActive;
    }

    public String getAccountingDistributionTemplateName() {
        return accountingDistributionTemplateName;
    }

    public String getDeliveryAddressDescription() {
        return deliveryAddressDescription;
    }

    public String getVendorTransactionSettlementType() {
        return vendorTransactionSettlementType;
    }

    public String getDeliveryCityInKana() {
        return deliveryCityInKana;
    }

    public String getDeliveryStreetInKana() {
        return deliveryStreetInKana;
    }

    public String getReasonComment() {
        return reasonComment;
    }

    public String getNumberSequenceGroupId() {
        return numberSequenceGroupId;
    }

    public String getTransportationTemplateId() {
        return transportationTemplateId;
    }

    public String getAccountingDate() {
        return accountingDate;
    }

    public Integer getCashDiscountPercentage() {
        return cashDiscountPercentage;
    }

    public String getPurchaseOrderName() {
        return purchaseOrderName;
    }

    public String getRequestedDeliveryDate() {
        return requestedDeliveryDate;
    }

    public String getDeliveryAddressCountryRegionId() {
        return deliveryAddressCountryRegionId;
    }

    public Integer getDeliveryAddressLatitude() {
        return deliveryAddressLatitude;
    }

    public String getMultilineDiscountVendorGroupCode() {
        return multilineDiscountVendorGroupCode;
    }

    public String getDeliveryAddressCity() {
        return deliveryAddressCity;
    }

    public String getConfirmedDeliveryDate() {
        return confirmedDeliveryDate;
    }

    public String getPurchaseRebateVendorGroupId() {
        return purchaseRebateVendorGroupId;
    }

    public String getInvoiceAddressCounty() {
        return invoiceAddressCounty;
    }

    public String getChargeVendorGroupId() {
        return chargeVendorGroupId;
    }

    public String getRequesterPersonnelNumber() {
        return requesterPersonnelNumber;
    }

    public String getProjectId() {
        return projectId;
    }

    public String getShippingCarrierId() {
        return shippingCarrierId;
    }

    public Integer getTotalDiscountPercentage() {
        return totalDiscountPercentage;
    }

    public String getPriceVendorGroupCode() {
        return priceVendorGroupCode;
    }

    public String getPurchaseOrderHeaderCreationMethod() {
        return purchaseOrderHeaderCreationMethod;
    }

    public String getDeliveryAddressDistrictName() {
        return deliveryAddressDistrictName;
    }

    public String getDeliveryAddressCountyId() {
        return deliveryAddressCountyId;
    }

    public String getDeliveryAddressZipCode() {
        return deliveryAddressZipCode;
    }

    public String getIsConsolidatedInvoiceTarget() {
        return isConsolidatedInvoiceTarget;
    }

    public String getConfirmingPurchaseOrderCode() {
        return confirmingPurchaseOrderCode;
    }

    public String getLanguageId() {
        return languageId;
    }

    public String getReasonCode() {
        return reasonCode;
    }

    public String getDeliveryAddressDunsNumber() {
        return deliveryAddressDunsNumber;
    }

    public String getDeliveryTermsId() {
        return deliveryTermsId;
    }

    public String getBankDocumentType() {
        return bankDocumentType;
    }

    public String getExpectedStoreReceiptDate() {
        return expectedStoreReceiptDate;
    }

    public String getDeliveryAddressName() {
        return deliveryAddressName;
    }

    public String getInvoiceAddressCountryRegionId() {
        return invoiceAddressCountryRegionId;
    }

    public String getReplenishmentServiceCategoryId() {
        return replenishmentServiceCategoryId;
    }

    public String getPurchaseOrderPoolId() {
        return purchaseOrderPoolId;
    }

    public String getDeliveryAddressStreetNumber() {
        return deliveryAddressStreetNumber;
    }

    public String getExpectedCrossDockingDate() {
        return expectedCrossDockingDate;
    }

    public String getInvoiceAddressStreetNumber() {
        return invoiceAddressStreetNumber;
    }

    public String getTaxExemptNumber() {
        return taxExemptNumber;
    }

    public String getIsDeliveryAddressPrivate() {
        return isDeliveryAddressPrivate;
    }

    public String getFormattedInvoiceAddress() {
        return formattedInvoiceAddress;
    }

    public String getBuyerGroupId() {
        return buyerGroupId;
    }

    public String getDeliveryAddressCountryRegionISOCode() {
        return deliveryAddressCountryRegionISOCode;
    }

    public String getCashDiscountCode() {
        return cashDiscountCode;
    }

    public String getPaymentScheduleName() {
        return paymentScheduleName;
    }

    public String getIntrastatTransactionCode() {
        return intrastatTransactionCode;
    }

    public String getuRL() {
        return uRL;
    }

    public String getConfirmingPurchaseOrderCodeLanguageId() {
        return confirmingPurchaseOrderCodeLanguageId;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public String getInvoiceType() {
        return invoiceType;
    }

    public String getArePricesIncludingSalesTax() {
        return arePricesIncludingSalesTax;
    }

    public String getDeliveryAddressLocationId() {
        return deliveryAddressLocationId;
    }

    public String getgSTSelfBilledInvoiceApprovalNumber() {
        return gSTSelfBilledInvoiceApprovalNumber;
    }

    public String getIsDeliveredDirectly() {
        return isDeliveredDirectly;
    }

    public String getIntrastatStatisticsProcedureCode() {
        return intrastatStatisticsProcedureCode;
    }

    public String getInvoiceVendorAccountNumber() {
        return invoiceVendorAccountNumber;
    }

    public String getDeliveryAddressStreet() {
        return deliveryAddressStreet;
    }

    public String getVendorOrderReference() {
        return vendorOrderReference;
    }

    public String getReplenishmentWarehouseId() {
        return replenishmentWarehouseId;
    }

    public String getFixedDueDate() {
        return fixedDueDate;
    }

    public String getTransportationDocumentLineId() {
        return transportationDocumentLineId;
    }

    public String getSalesTaxGroupCode() {
        return salesTaxGroupCode;
    }

    public String getIsDeliveryAddressOrderSpecific() {
        return isDeliveryAddressOrderSpecific;
    }

    public String getVendorPostingProfileId() {
        return vendorPostingProfileId;
    }

    public String getVendorPaymentMethodSpecificationName() {
        return vendorPaymentMethodSpecificationName;
    }

    public String getInvoiceAddressCity() {
        return invoiceAddressCity;
    }

    public String getShippingCarrierServiceGroupId() {
        return shippingCarrierServiceGroupId;
    }

    public String getContactPersonId() {
        return contactPersonId;
    }

    public String getDefaultReceivingWarehouseId() {
        return defaultReceivingWarehouseId;
    }

    public String geteUSalesListCode() {
        return eUSalesListCode;
    }

    public String getImportDeclarationNumber() {
        return importDeclarationNumber;
    }

    public String getPurchaseOrderStatus() {
        return purchaseOrderStatus;
    }

    public String getPaymentTermsName() {
        return paymentTermsName;
    }

    public Integer getDeliveryAddressLongitude() {
        return deliveryAddressLongitude;
    }

    public String getDocumentApprovalStatus() {
        return documentApprovalStatus;
    }

    public String getInvoiceAddressZipCode() {
        return invoiceAddressZipCode;
    }

    public String getShippingCarrierServiceId() {
        return shippingCarrierServiceId;
    }

    public String getDefaultLedgerDimensionDisplayValue() {
        return defaultLedgerDimensionDisplayValue;
    }

    public Object getDeliveryAddressTimeZone() {
        return deliveryAddressTimeZone;
    }

    public String getAttentionInformation() {
        return attentionInformation;
    }

    public String getDeliveryAddressStateId() {
        return deliveryAddressStateId;
    }

    public String getDeliveryBuildingCompliment() {
        return deliveryBuildingCompliment;
    }

    public String getIntrastatTransportModeCode() {
        return intrastatTransportModeCode;
    }

    public String getDeliveryAddressPostBox() {
        return deliveryAddressPostBox;
    }

    public String getIsOneTimeVendor() {
        return isOneTimeVendor;
    }

    public String getIntrastatPortId() {
        return intrastatPortId;
    }

    public String getOrdererPersonnelNumber() {
        return ordererPersonnelNumber;
    }

    public String getVendorPaymentMethodName() {
        return vendorPaymentMethodName;
    }

    public String getInvoiceAddressState() {
        return invoiceAddressState;
    }

    public String getDefaultReceivingSiteId() {
        return defaultReceivingSiteId;
    }

    public String getLineDiscountVendorGroupCode() {
        return lineDiscountVendorGroupCode;
    }

    public String getTransportationRoutePlanId() {
        return transportationRoutePlanId;
    }

    public String getZakatContractNumber() {
        return zakatContractNumber;
    }

    public String getFormattedDeliveryAddress() {
        return formattedDeliveryAddress;
    }

    public String getTotalDiscountVendorGroupCode() {
        return totalDiscountVendorGroupCode;
    }
}
