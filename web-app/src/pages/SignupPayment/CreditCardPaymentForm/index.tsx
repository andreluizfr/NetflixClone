import './styles.css';
import { useEffect, useState } from "react";
import useMercadoPago from "../../../hooks/UseMercadoPago";

import { useSelector } from 'react-redux';
import { StoreState } from '../../../store';
import { useNavigate } from "react-router-dom";

import Plan from "../../../types/Plan";
import CreatePlanPaymentQuery from "../../../queries/Payments/CreatePlanPayment";
import { useLocalStorage } from "../../../hooks/UseLocalStorage";
import { User } from "../../../types/User";


export default function UseMercadoPagoForm () {

    const navigate = useNavigate();
    
    const signup = useSelector((state: StoreState) => state.signup);

    const [userString, setUserString] = useLocalStorage("user", "null");
    const [user, setUser] = useState<User | null>(JSON.parse(userString) as unknown as User);

    const mercadopago = useMercadoPago(import.meta.env.VITE_MERCADO_PAGO_PUBLIC_KEY, {
        locale: 'pt-BR'
    });

    const [paymentForm, setPaymentForm] = useState<{
        token: string,
        issuer_id: string,
        payment_method_id: string,
        transaction_amount: number,
        installments: number,
        description: string,
        payer: {
            email: string,
            identification: {
                type: string,
                number: number,
            }
        }
    } | null>(null);

    const createPlanPaymentQuery = CreatePlanPaymentQuery(paymentForm, user?.account.id, signup.plan, signup.paymentType);

    //cria o formulário para cartão e seus callbacks
    useEffect(()=>{

        if(mercadopago){

            let amount;

            if(signup.paymentType != null && signup.plan != null){

                switch(signup.plan){
                    case Plan.BASIC_WITH_ADS:
                        amount = 19.90;
                        break;
                    case Plan.BASIC:
                        amount = 39.90;
                        break;
                    case Plan.PREMIUM:
                        amount = 59.90;
                        break;
                }

                const cardForm = mercadopago.cardForm({
                    amount: String(amount),
                    iframe: true,
                    form: 
                    {
                        id: "form-checkout",
                        cardNumber: {
                            id: "form-checkout__cardNumber",
                            placeholder: "Número do cartão",
                        },
                        expirationDate: {
                            id: "form-checkout__expirationDate",
                            placeholder: "MM/YY",
                        },
                        securityCode: {
                            id: "form-checkout__securityCode",
                            placeholder: "Código de segurança",
                        },
                        cardholderName: {
                            id: "form-checkout__cardholderName",
                            placeholder: "Titular do cartão",
                        },
                        issuer: {
                            id: "form-checkout__issuer",
                            placeholder: "Banco emissor",
                        },
                        installments: {
                            id: "form-checkout__installments",
                            placeholder: "Parcelas",
                        },        
                        identificationType: {
                            id: "form-checkout__identificationType",
                            placeholder: "Tipo de documento",
                        },
                        identificationNumber: {
                            id: "form-checkout__identificationNumber",
                            placeholder: "Número do documento",
                        },
                        cardholderEmail: {
                            id: "form-checkout__cardholderEmail",
                            placeholder: "E-mail",
                        },
                    },
                    callbacks: {
                        onFormMounted: (error: Error) => {
                            if (error) return console.warn("Form Mounted handling error: ", error);
                            console.log("Form mounted");
                        },
                        onSubmit: (event: React.FormEvent <HTMLFormElement>) => {
                            event.preventDefault();
              
                            let {
                                paymentMethodId: payment_method_id,
                                issuerId: issuer_id,
                                cardholderEmail: email,
                                amount,
                                token,
                                installments,
                                identificationNumber,
                                identificationType,
                            } = cardForm.getCardFormData();
    
                            const description = "Plan: " + signup.plan;

                            setPaymentForm({
                                token,
                                issuer_id,
                                payment_method_id,
                                transaction_amount: Number(amount),
                                installments: Number(installments),
                                description: description,
                                payer: {
                                    email,
                                    identification: {
                                        type: identificationType,
                                        number: identificationNumber,
                                    }
                                }
                            });
                      },
                      onFetching: (resource: string) => {
                        console.log("Fetching resource: ", resource);
              
                        // Animate progress bar
                        const progressBar = document.querySelector(".Progress-bar");
                        if(progressBar)
                            progressBar.removeAttribute("value");
              
                        return () => {
                            if(progressBar)
                                progressBar.setAttribute("value", "0");
                        };
                      }
                    },
                  }
                );

            } else {

                console.log("Informações para pagamento não encontradas.");
                navigate("/signup");

            }
            
        }
    // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [mercadopago]);

    //pega todos os tipos de identificação do mercado pago
    useEffect(()=>{
        if(mercadopago)
            mercadopago.getIdentificationTypes().then(identificationTypes=>{
                const identificationTypeElement = document.getElementById('form-checkout__identificationType') as HTMLSelectElement;
                if(identificationTypeElement)
                    createSelectOptions(identificationTypeElement, identificationTypes);
            }).catch(e=>{
                console.error('Error getting identificationTypes: ', e);
            });
    }, [mercadopago]);

    function createSelectOptions(elem: HTMLSelectElement, options: {[key: string]: string}[], labelsAndKeys = { label: "name", value: "id" }) {
        
        const { label, value } = labelsAndKeys;
  
        elem.options.length = 0;
  
        const tempOptions = document.createDocumentFragment();
  
        options.forEach(option => {
          const optValue = option[value];
          const optLabel = option[label];
  
          const opt = document.createElement('option');
          opt.value = optValue;
          opt.textContent = optLabel;
  
          tempOptions.appendChild(opt);
        });
  
        elem.appendChild(tempOptions);
    }

    useEffect(()=>{
        if(paymentForm)
            createPlanPaymentQuery.refetch();
    // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [paymentForm]);

    useEffect(()=>{
        if(createPlanPaymentQuery?.data?.payment)
            console.log(createPlanPaymentQuery.data.payment);
    // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [createPlanPaymentQuery.data]);

    return (
        <form id="form-checkout">
            <div className="Input" id="form-checkout__cardNumber"></div>
            <div className="Input" id="form-checkout__expirationDate"></div>
            <div className="Input" id="form-checkout__securityCode"></div>

            <input className="Input" type="text" id="form-checkout__cardholderName"/>

            <select className="Select" id="form-checkout__issuer">
            </select>

            <select className="Select" id="form-checkout__installments">
            </select>

            <select className="Select" id="form-checkout__identificationType">
            </select>

            <input className="Input" type="text" id="form-checkout__identificationNumber"/>

            <input className="Input" type="email" id="form-checkout__cardholderEmail"/>

            <button className="Pay-button" type="submit" id="form-checkout__submit">Pagar</button>

            <progress className="Progress-bar" value="0">Carregando...</progress>

            <div>{JSON.stringify(createPlanPaymentQuery.data)}</div>
        </form>
    )
    
}