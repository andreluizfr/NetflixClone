import { IQueryResult } from '@Application/interfaces/queryManager/IQueryResult';

import { Plan } from '@Model/entities/Account';
import { PaymentType } from '@Model/types/PaymentType';
import { PreferenceResponse } from '@Model/types/PreferenceResponse';

export type ICreatePlanPayment = (
    accountId: string | undefined,
    plan: Plan | null, 
    paymentType: PaymentType | null
) => IQueryResult<PreferenceResponse>;