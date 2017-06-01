'use strict';

const React = require('react');
const ReactDOM = require('react-dom')
const client = require('./client');

class App extends React.Component {

    constructor(props) {
        super(props);
        this.state = {accounts: []};
    }

    componentDidMount() {
        client({method: 'GET', path: '/api/accounts'}).done(response => {
            this.setState({accounts: response.entity});
        });
    }

    render() {
        return (
            <AccountDropDownList accounts={this.state.accounts}/>
        )
    }
}

class AccountDropDownList extends React.Component {
    render() {
        var accounts = this.props.accounts.map(account =>
            <Account key={account.id} account={account}/>
        );
        return (
            <select>
                {accounts}
            </select>
        )
    }
}

class Account extends React.Component {
    render() {
        return (
            <option value={this.props.account.id}>{this.props.account.name}</option>
        )
    }
}

ReactDOM.render(
    <App />,
    document.getElementById('react')
)

