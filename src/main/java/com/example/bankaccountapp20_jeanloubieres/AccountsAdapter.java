package com.example.bankaccountapp20_jeanloubieres;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AccountsAdapter extends RecyclerView.Adapter<AccountsAdapter.ViewHolder> {

    public ArrayList<Accounts> accounts = new ArrayList<>();
    private Context context;

    public AccountsAdapter(Context context, ArrayList<Accounts> accounts) {
        this.accounts = accounts;
        this.context = context;
    }

    @NonNull
    @Override
    public AccountsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.accounts_list_item, viewGroup, false);
        return new AccountsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AccountsAdapter.ViewHolder viewHolder, int i) {
        viewHolder.account_accountName.setText(accounts.get(i).getAccountName());
        viewHolder.account_amount.setText(accounts.get(i).getAmount());
        viewHolder.account_iban.setText(accounts.get(i).getIban());
        viewHolder.account_currency.setText(accounts.get(i).getCurrency());
    }

    @Override
    public int getItemCount() {
        return accounts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView account_accountName;
        private final TextView account_amount;
        private final TextView account_iban;
        private final TextView account_currency;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            account_accountName = (TextView)itemView.findViewById(R.id.account_accountName);
            account_amount = (TextView)itemView.findViewById(R.id.account_amount);
            account_iban = (TextView)itemView.findViewById(R.id.account_iban);
            account_currency = (TextView)itemView.findViewById(R.id.account_currency);
        }
    }
}
