import './styles.css';
import profilePicture from '../../assets/img/profile-picture.jpg';
import * as DropdownMenu from '@radix-ui/react-dropdown-menu';
//import { motion, useAnimation } from 'framer-motion';
import { motion } from 'framer-motion';

import { Link } from 'react-router-dom';

import { useDispatch } from 'react-redux';

import { makePersistentStorage } from '@Main/factories/infrastructure/makePersistentStorage';

import { removeUser } from '@Infrastructure/stores/redux/features/userSlice';


export default function ProfileDropdownMenu(): JSX.Element {
    /*
    const profileIconControls = useAnimation();
    
    function animateProfileIcon (){
        profileIconControls.start({rotate: [45, -45, 45, 0]});
    };
    */
   const persistentStorage = makePersistentStorage();

   const dispatch = useDispatch();

    function logout(){
        dispatch(removeUser());
        persistentStorage.remove("x-access-token");
    }
    
    return(
        <DropdownMenu.Root>
            <DropdownMenu.Trigger className="ProfileDropdownMenuTrigger">
                <motion.img
                    className='Profile-icon'
                    alt='profile icon' 
                    src={profilePicture}
                    //animate={profileIconControls}
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
                            (new Array(10)).fill(0).map((index: number)=>{
                                return (
                                    <motion.li className='ProfileDropdownMenuItem' key={"profileDropdownMenuItem-"+index}>
                                        <Link to="#" className='Item-container'>
                                            <img className='' src={""} alt="profile icon"/>
                                            <span className='ProfileName'>{""}</span>
                                        </Link>
                                    </motion.li>
                                )
                            })
                        }

                        <motion.li className='ProfileDropdownMenuItem'>
                            <Link to="#" className='Item-container'>
                                <img className='' src={""} alt="manage icon"/>
                                <span className='Option'>Gerenciar perfis </span>
                            </Link>
                        </motion.li>

                        <motion.li className='ProfileDropdownMenuItem'>
                            <Link to="#" className='Item-container'>
                                <img className='' src={""} alt="transfer icon"/>
                                <span className='Option'>Transferir perfil</span>
                            </Link>
                        </motion.li>

                        <motion.li className='ProfileDropdownMenuItem'>
                            <Link to="#" className='Item-container'>
                                <img className='' src={""} alt="account icon"/>
                                <span className='Option'>Conta</span>
                            </Link>
                        </motion.li>

                        <motion.li className='ProfileDropdownMenuItem'>
                            <Link to="#" className='Item-container'>
                                <img className='' src={""} alt="help icon"/>
                                <span className='Option'>Central de Ajuda</span>
                            </Link>
                        </motion.li>

                        <motion.li className='ProfileDropdownMenuItem'>
                            <div className='Item-container' onClick={logout}>
                                <img className='' src={""} alt="help icon"/>
                                <span className='Option'>SAIR (Temporário)</span>
                            </div>
                        </motion.li>
                        
                    </motion.ul>
                </DropdownMenu.Content>
            </DropdownMenu.Portal>
        </DropdownMenu.Root>
    );
}