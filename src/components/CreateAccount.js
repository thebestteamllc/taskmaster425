import React, { useState } from "react";
import '../createAccount.css';

function CreateAccount({stateFunction, onDismiss}) {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    const handleCreateAccount = () => {
        console.log('clicked');
        stateFunction(true);
    };

    const handleDismissal = () => {
        onDismiss(false);
    }

    return (
        <div className="title-page">
            <div className="title-container">
                <h1 className="title">EasySprint</h1>
            </div>
            <div className="create-account-container">
                <h2>Create Account</h2>
                <form>
                    <label>Email:
                        <input
                        type="text"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                        />
                    </label>
                    <label>Password:
                        <input
                        type="password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        />
                    </label>
                    <label>Re-enter Password:
                        <input
                        type="password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        />
                    </label>
                    {/*Uses handleDismassal on Creat Account since backend is not connected.*/}
                    {/*<button onClick={handleCreateAccount}>Create Account</button>*/}
                    <button onClick={handleDismissal}>Create Account</button>
                    <p>Already have an account? <a onClick={handleDismissal}>Sign in</a></p>
                </form>
            </div>
            <footer>
                2023 T5 Software
            </footer>
        </div>
    );
}

export default CreateAccount;