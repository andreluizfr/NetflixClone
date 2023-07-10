export interface PreferenceResponse {
    response: {
        statusCode: number,
        headers: object,
        content: string
    },
    id: string,
    items: [
        {
            id: string,
            title: string,
            description: string,
            pictureUrl: null,
            categoryId: string,
            quantity: number,
            unitPrice: number,
            currencyId: string
        }
    ],
    payer: {
        name: string,
        surname: string,
        email: string,
        phone: {
            areaCode: string,
            number: string
        },
        identification: {
            type: string,
            number: string
        },
        address: {
            zipCode: string,
            streetName: string,
            streetNumber: null | string
        },
        dateCreated: null | Date,
        lastPurchase: null | Date
    },
    clientId: number,
    paymentMethods: {
        excludedPaymentMethods: object[],
        excludedPaymentTypes: object[],
        defaultPaymentMethodId: null | string,
        installments: null | number,
        defaultInstallments: null | number
    },
    backUrls: {
        success: string,
        pending: string,
        failure: string
    },
    shipments: {
        mode: null | string,
        localPickup: null | string,
        dimensions: null | string,
        defaultShippingMethod: null | string,
        freeMethods: null | string,
        cost: null | string,
        freeShipping: null | string,
        receiverAddress: {
            zipCode: string,
            streetName: string,
            streetNumber: null | string,
            countryName: null | string,
            stateName: null | string,
            floor: string,
            apartment: string,
            cityName: null | string
        },
        expressShipment: null | string
    },
    notificationUrl: null | string,
    statementDescriptor: null | string,
    externalReference: string,
    expires: boolean,
    dateOfExpiration: null,
    expirationDateFrom: null,
    expirationDateTo: null,
    collectorId: number,
    marketplace: string,
    marketplaceFee: number,
    additionalInfo: string,
    autoReturn: string,
    operationType: string,
    differentialPricing: null | number,
    processingModes: null | string,
    binaryMode: boolean,
    taxes: null | number,
    tracks: null | string,
    metadata: object,
    initPoint: string,
    sandboxInitPoint: string,
    dateCreated: Date
}
