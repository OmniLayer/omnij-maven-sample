/*
 * Copyright 2021 M. Sean Gilligan.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package foundation.omni.sample.maven;

import foundation.omni.CurrencyID;
import foundation.omni.money.OmniCurrencyCode;
import foundation.omni.net.OmniMainNetParams;
import foundation.omni.netapi.WalletAddressBalance;
import foundation.omni.netapi.omniwallet.OmniwalletAbstractClient;
import foundation.omni.rest.omniwallet.mjdk.OmniwalletModernJDKClient;
import foundation.omni.rpc.BalanceEntry;
import org.bitcoinj.core.Address;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Simple test tool that uses {@link OmniwalletAbstractClient} to print the current balances of the Exodus address.
 */
public class OmniwalletSample {
    static final Address exodus = OmniMainNetParams.get().getExodusAddress();


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("Getting Exodus Address balance from " + OmniwalletAbstractClient.omniwalletBase + " ...");

        // Create an Omniwallet Client for the default URL
        OmniwalletAbstractClient client = new OmniwalletModernJDKClient(OmniwalletAbstractClient.omniwalletBase);

        // Build a singleton list containing the Exodus address
        List<Address> addressList = Collections.singletonList(exodus);

        // Query Omniwallet for the balances
        WalletAddressBalance balance = client.balancesForAddressesAsync(addressList).get().get(exodus);

        // Print the balance for each property
        balance.forEach(OmniwalletSample::printCurrencyBalance);

        // Exit successfully
        System.exit(0);
    }

    static void printCurrencyBalance(CurrencyID id, BalanceEntry bal) {
        System.out.println(idToSymbol(id) + ": " + bal.getBalance().toPlainString());
    }

    static String idToSymbol(CurrencyID id) {
        return OmniCurrencyCode.idToCodeString(id);
    }
}
