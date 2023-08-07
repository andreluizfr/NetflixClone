import './styles.css';
import logo from '../../assets/svg/logo.svg';
import addButton from '@Presentation/assets/svg/add-button.svg';

import { StoreState } from '@Infrastructure/stores/redux/config';
import { removeUser, setProfile } from '@Infrastructure/stores/redux/features/userSlice';
import { useDispatch, useSelector } from 'react-redux';

import { Link, useNavigate } from "react-router-dom";
import { useEffect } from 'react';

import { motion } from 'framer-motion';

import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import { Profile } from '@Model/entities/Profile';

export default function WhoIsWatchingPage(): JSX.Element {

    //  ############# Redirecionamento de página ##################
    const dispatch = useDispatch();
    const navigate = useNavigate();
    const user = useSelector((state: StoreState) => state.user);

    useEffect(()=>{
        
        if(user.data && !user.data.account.active){
            toast.error("Ainda não identificamos o seu pagamento", {
                position: "top-center",
                hideProgressBar: true
            });
            dispatch(removeUser());
            setTimeout(()=>navigate("/signup/planform"), 2000);
        }
        
    }, [user.data]);

    function goToContents(profile: Profile){
        dispatch(setProfile(profile));
        navigate("/contents");
    }


    //  ############# Renderização do conteúdo ##################
    const width = window.innerWidth;

    return(
        <motion.div 
            className='WhoIsWatchingPage'
            initial={{ x: -(width/2), opacity: 0}}
			animate={{x: 0, opacity: 1, transition:{type: "easeIn", duration: 0.6}}}
        >
            <ToastContainer
                autoClose={2000} //mesmo tempo do redirecionamento feito pelo serviço do login
                newestOnTop={true}
                pauseOnFocusLoss={false}
                draggable={false}
                pauseOnHover={false}
                hideProgressBar={false}
                theme="dark"
            />

            <header className='Header'>
                <Link to="/">
                    <img className='Logo' src={logo} alt="Netflix Logo"/>
                </Link>
            </header>

            <main className='Who-is-watching-container'>
                
                <div className='Title'>
                    Quem está assistindo?
                </div>

                <div className='Profiles'>
                    {user.data?.account.profiles.map((profile: Profile, index: number)=>{
                        return(
                            <div className='Profile-container' key={"profile-"+index} onClick={()=>goToContents(profile)}>
                                <img className='Icon' src={"profileIcons/classic/"+profile.iconCod+".png"}/>
                                <span className='Owner-name'>{profile.ownerName}</span>
                            </div>
                        );
                    })}

                    <div className='Add-profile'>
                        <img className='Icon' src={addButton}/>
                        <span className='Description'>Adicionar perfil</span>
                    </div>  
                </div>

                <button className='Manage-profiles-button'>
                    Gerenciar perfis
                </button>
                
            </main>

        </motion.div>
    )

}