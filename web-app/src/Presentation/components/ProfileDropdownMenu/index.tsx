import './styles.css';
import * as DropdownMenu from '@radix-ui/react-dropdown-menu';

import { motion, useAnimation } from 'framer-motion';

import { useNavigate } from 'react-router-dom';

import { useDispatch, useSelector } from 'react-redux';

import { makePersistentStorage } from '@Main/factories/infrastructure/makePersistentStorage';

import { removeUser } from '@Infrastructure/stores/redux/features/userSlice';
import { useEffect } from 'react';
import { StoreState } from '@Infrastructure/stores/redux/config';
import { Profile } from '@Model/entities/Profile';


export default function ProfileDropdownMenu(): JSX.Element {
    
   //  ############# Redirecionamento de página ##################
   const persistentStorage = makePersistentStorage();
   const dispatch = useDispatch();
   const navigate = useNavigate();

    function logout(){
        dispatch(removeUser());
        persistentStorage.remove("x-access-token");
        setTimeout(()=>navigate("/"), 500);
    }


    //  ############# Renderização do conteúdo ##################
    const profileIconControls = useAnimation();
    
    function animateProfileIcon (){
        profileIconControls.start({rotate: [45, -45, 45, 0]});
    };

    useEffect(()=>{
        animateProfileIcon();
    }, []);

    const user = useSelector((state: StoreState) => state.user);

    return(
        <DropdownMenu.Root>
            <DropdownMenu.Trigger className="ProfileDropdownMenuTrigger">
                <motion.img
                    className='Profile-icon'
                    alt='profile icon' 
                    src={"profileIcons/classic/"+user.selectedProfile?.iconCod+".png"}
                    animate={profileIconControls}
                    transition={{type:'spring', bounce: 0.8}}
                />
            </DropdownMenu.Trigger>

            <DropdownMenu.Portal>
                <DropdownMenu.Content className="ProfileDropdownMenuContent" sideOffset={5} asChild forceMount>
                    <motion.ul
                        initial={{ scale: 0, opacity: 0 }}
                        animate={{
                            scale: 1,
                            opacity: 1,
                            transition: { type: "spring", duration: 0.3 },
                        }}    
                    >
                        {
                            user.data?.account.profiles.map((profile: Profile, index: number)=>{
                                return (
                                    <motion.li className='ProfileDropdownMenuItem' key={"profileDropdownMenuItem-"+index}>
                                        <img className='Profile-icon' src={"profileIcons/classic/"+profile.iconCod+".png"} alt="profile icon"/>
                                        <span className='Profile-name'>{profile.ownerName}</span>
                                    </motion.li>
                                )
                            })
                        }

                        <motion.li className='ProfileDropdownMenuItem'>
                            <img className='Icon' src={""} alt="manage icon"/>
                            <span className='Option'>Gerenciar perfis </span>
                        </motion.li>

                        <motion.li className='ProfileDropdownMenuItem'>
                            <img className='Icon' src={""} alt="transfer icon"/>
                            <span className='Option'>Transferir perfil</span>
                        </motion.li>

                        <motion.li className='ProfileDropdownMenuItem'>
                            <img className='Icon' src={""} alt="account icon"/>
                            <span className='Option'>Conta</span>
                        </motion.li>

                        <motion.li className='ProfileDropdownMenuItem'>
                            <img className='Icon' src={""} alt="help icon"/>
                            <span className='Option'>Central de Ajuda</span>
                        </motion.li>

                        <motion.li className='ProfileDropdownMenuItem'>
                            <div className='Item-container' onClick={logout}>
                                <img className='Icon' src={""} alt="help icon"/>
                                <span className='Option'>Sair</span>
                            </div>
                        </motion.li>
                        
                    </motion.ul>
                </DropdownMenu.Content>
            </DropdownMenu.Portal>
        </DropdownMenu.Root>
    );
}