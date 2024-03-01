-- PROCEDURE: public.add_percent()

-- DROP PROCEDURE IF EXISTS public.add_percent();

CREATE OR REPLACE PROCEDURE public.add_percent(
	)
LANGUAGE 'plpgsql'
AS $BODY$
DECLARE
    amount_record RECORD;
	current_amount_percent numeric;
BEGIN
    -- Ваш код здесь
    -- Например, выполнение каких-либо операций с параметром
    LOCK TABLE account IN SHARE MODE;
	FOR amount_record IN SELECT amount, amount_percent, id FROM account LOOP
		current_amount_percent := (amount_record.amount+amount_record.amount_percent)*1.05;
		if current_amount_percent >= (amount_record.amount*2.07) then
			RAISE NOTICE 'Title: %, Length: %', amount_record.amount, amount_record.amount_percent;
			current_amount_percent := amount_record.amount_percent;
		else 
			current_amount_percent := current_amount_percent-amount_record.amount;
		end if;
		update account
		set amount_percent = current_amount_percent
		where id = amount_record.id;
    
    END LOOP;
	
END;
$BODY$;
ALTER PROCEDURE public.add_percent()
    OWNER TO postgres;
