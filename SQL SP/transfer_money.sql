-- PROCEDURE: public.transfer_money(bigint, bigint, numeric)

-- DROP PROCEDURE IF EXISTS public.transfer_money(bigint, bigint, numeric);

CREATE OR REPLACE PROCEDURE public.transfer_money(
	IN id_from bigint,
	IN id_to bigint,
	IN amount_transfer numeric)
LANGUAGE 'plpgsql'
AS $BODY$
DECLARE
	amount_from record;
	amount_to record;
	current_amount numeric;
BEGIN
 
    LOCK TABLE account IN SHARE MODE;
	select amount,amount_percent into amount_from from account where id = id_from;
	select amount,amount_percent into amount_to from account where id = id_to;
	
	current_amount := amount_from.amount + amount_from.amount_percent;
	
	IF current_amount < amount_transfer THEN
        RAISE EXCEPTION 'amount_from меньше amount_to';
	else 
		if amount_from.amount_percent >= amount_transfer then 
			amount_from.amount_percent := amount_from.amount_percent-amount_transfer;
		else 
			amount_from.amount := amount_from.amount - (amount_transfer-amount_from.amount_percent);
			amount_from.amount_percent:= 0.00;
		end if;
    END IF;
	
	update account 
	set amount = amount_from.amount
	where id = id_from;
	
	update account 
	set amount_percent = amount_from.amount_percent
	where id = id_from;
	
	update account 
	set amount = amount_to.amount+amount_transfer 
	where id = id_to;
	

END;
$BODY$;
ALTER PROCEDURE public.transfer_money(bigint, bigint, numeric)
    OWNER TO postgres;
